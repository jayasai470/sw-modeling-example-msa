package com.autoinsurance;

import org.metaworks.springboot.configuration.Metaworks4BaseApplication;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@SpringBootApplication
@EnableEurekaClient
@EnableWebMvc
public class CustomerServiceApplication extends Metaworks4BaseApplication {

	/**
	 * @param dataSource
	 * @param properties
	 * @param jtaTransactionManagerProvider
	 */
	protected CustomerServiceApplication(DataSource dataSource, JpaProperties properties,
										 ObjectProvider<JtaTransactionManager> jtaTransactionManagerProvider,
										 ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
		super(dataSource, properties, jtaTransactionManagerProvider, transactionManagerCustomizers);
	}





	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(CustomerServiceApplication.class, args);

	}

}