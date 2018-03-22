package com.autoinsurance;

import com.autoinsurance.services.CreditService;
import org.metaworks.springboot.configuration.Metaworks4BaseApplication;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackageClasses = {CreditService.class})
public class InsuranceServiceApplication extends Metaworks4BaseApplication {

	/**
	 * @param dataSource
	 * @param properties
	 * @param jtaTransactionManagerProvider
	 */
	protected InsuranceServiceApplication(DataSource dataSource, JpaProperties properties,
										  ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider,
										  ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, properties, jtaTransactionManagerProvider, transactionManagerCustomizers);
	}





	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(InsuranceServiceApplication.class, args);
		CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);

		Customer customer = new Customer();
		customer.setFirstName("Rick");
		customer.setLastName("Jang");
		customer.setSsn("12");

		Vehicle vehicle = new Vehicle();
		vehicle.setBrand("BMW");
		vehicle.setCustomer(customer);

		customerRepository.save(customer);

	}

}