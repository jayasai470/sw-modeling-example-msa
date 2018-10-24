package com.autoinsurance;

import com.autoinsurance.services.CreditService;
import org.metaworks.multitenancy.persistence.MultitenantRepositoryImpl;
import org.metaworks.springboot.configuration.Metaworks4BaseApplication;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@EnableFeignClients(basePackageClasses = {CreditService.class})
@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableEurekaClient
@RestController
@EnableCircuitBreaker
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = MultitenantRepositoryImpl.class)

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

//		CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);
//
//		Customer customer = new Customer();
//		customer.setFirstName("Rick");
//		customer.setLastName("Jang");
//		customer.setSsn("12");
//
//		Vehicle vehicle = new Vehicle();
//		vehicle.setBrand("BMW");
//		vehicle.setCustomer(customer);
//
//		customerRepository.save(customer);

	}

	@Override
	@Bean
	@Primary
	protected AbstractJpaVendorAdapter createJpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}



}