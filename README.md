# sw-modeling-example-msa

## This is Micro Service Architecture edition of sw-modeling-example

### decomposition of services

There are three separated services governed by different organizations:

1. Credit Service
2. DMV (Department of motor vehicle) Service
3. Insurance Service

### Implementing Credit Service

The service will store the credit information by SSN and user can search by uri pattern with SSN:
```json
http localhost:8083/credits ssn="770921" creditRate="A”   #store the credit rate for SSN: 770921
http localhost:8083/credits/770921   #Get credit rate of 770921   

{
    "_links": {
        "credit": {
            "href": "http://localhost:8083/credits/770921"
        }, 
        "self": {
            "href": "http://localhost:8083/credits/770921"
        }
    }, 
    "creditRate": "A", 
    "ormid": "770921", 
    "saved": false
}

```

Create new project, and add pom.xml, Application.java, WebConfig.java copied from the sw-modeling-example's code. 
Firstly create an entity class named 'Credit.java'

```java
@Entity
public class Credit {

    @Id
    String ssn;

    String creditRate;

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(String creditRate) {
        this.creditRate = creditRate;
    }
}
```

And create the repository and give @RepositoryRestResource annotation tells spring to generate it as a RESTful Level 3 APIs:

```java
@RepositoryRestResource
public interface CreditRepository extends PagingAndSortingRepository<Credit, String>{
}
```

### Integration with credit service

Before saving the customer, insurance service need to confirm the credit rate of new coming customer, 

In the Customer.java, we need to add annotation '@PrePersist' to the Customer.java and implement the hooking method 'beforeSave' that will be invoked before saving the entity by the framework :

```java
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Customer")
public class Customer {
  
  
  ....


	@PrePersist
	public void beforeSave() {

		//REST 호출 to Credit 서비스로, 신용도를 리턴.
		//  http localhost:8083/credit/{getSocialNumber()}

		RestTemplate restTemplate = new RestTemplate();

		String uri = "http://localhost:8083/credits/" + getSocialNumber(); //registry 얻어

		ResponseEntity<String> response = null;
		response = restTemplate.exchange(uri,
				HttpMethod.GET, null, String.class);

		String result = response.getBody();

		String creditRate = JsonPath.read(result, "$.creditRate"); //직접적 msg 파싱 없앨



		if(creditRate.compareTo("B") > 0){
			throw new RuntimeException("Low credit rate");
		}
	}
  
}
```

[Note] To use the callback interface and method 'BeforeSave', your repository base interface must be 'MultitenantRepository' of metaworks4 framework, not the 'PagingAndSortingRepository'.

The the service:
```
http localhost:8081/customers firstName="jjy" ssn="770921"   #may succeed

http localhost:8083/credits ssn="770921" creditRate="C”
http localhost:8081/customers firstName="jjy" ssn="770921"   #may fail with "Low credit rate"

```


### Removing direct network address

In the previous example, there is a direct link to the credit service :

```java
	String uri = "http://localhost:8083/credits/" + getSocialNumber();

```

we can implicitly obtain the service's host name by Registry service prodived by EUREKA

EUREKA server is contained in this project:

To start up the eureka server:

```bash
  $ cd registry-service
  $ mvn spring-boot:run
```

After several seconds, go to http://localhost:8761/, you can find the services are up and listed in the registry.
Also we need to run the gateway service for integrating these uris from the services into single host:

```bash
  $ cd proxy-service
  $ mvn spring-boot:run
```

Now then we can access the services with single host localhost:8080:

```
http localhost:8080/credit-service/credits ssn="770921" creditRate="A"
http localhost:8080/customers firstName="jjy" ssn="770921"  

```

(You can check the path mappings configured in the application.yaml file of the proxy-service/src/main/resources)

One the dynamic service registration has been configured, you can then more easily integrate these micro-services with 'FeignClient':


```java
	@PrePersist
	public void beforeSave() {

		
		CreditService creditService = MetaworksRemoteService.getInstance().getBeanFactory().getBean(CreditService.class);
		
		String creditRate = creditService.getCredit(getSocialNumber()).getCreditRate();
		
		if(creditRate.compareTo("B") > 0){
			throw new RuntimeException("Low credit rate");
		}
	}

```

CreditService interface contains the service id that exposed and registered to the EUREKA, that look like this:
```

@FeignClient(serviceId = "credit-service")
public interface CreditService {

    @RequestMapping(path="/credits/{ssn}", method= RequestMethod.GET)
    public Credit getCredit(@PathVariable("ssn") String ssn);

}

```

The spring cloud generates a dynamic proxy for the service in the side of insurance-service so that communicates to the credit-service without implicitly getting the target ip addresses.
