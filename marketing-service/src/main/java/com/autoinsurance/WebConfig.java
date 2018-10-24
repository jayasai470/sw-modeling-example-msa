package com.autoinsurance;

import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.metaworks.multitenancy.ClassManager;
import org.metaworks.multitenancy.CouchbaseMetadataService;
import org.metaworks.multitenancy.DefaultMetadataService;
import org.metaworks.multitenancy.MetadataService;
import org.metaworks.multitenancy.tenantawarefilter.TenantAwareFilter;
import org.metaworks.springboot.configuration.Metaworks4WebConfig;
import org.metaworks.multitenancy.persistence.MultitenantRepositoryImpl;
import org.metaworks.rest.MetaworksRestService;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.uengine.modeling.resource.CachedResourceManager;
import org.uengine.modeling.resource.LocalFileStorage;
import org.uengine.modeling.resource.ResourceManager;
import org.uengine.modeling.resource.Storage;
import org.uengine.persistence.couchbase.CouchbaseStorage;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan(basePackageClasses = {MetaworksRestService.class, WebConfig.class, ClassManager.class, MetadataService.class, MultitenantRepositoryImpl.class})
@EnableJpaRepositories(repositoryBaseClass = MultitenantRepositoryImpl.class)
public class WebConfig extends Metaworks4WebConfig {


    @Bean
    /**
     *
     * <bean class="CouchbaseStorage">
     *    <property name="basePath" value="/"/>
     <property name="bucketName" value="default"/>
     <property name="serverIp" value="localhost"/>
     </bean>
     */
    public Storage storage() {
        LocalFileStorage storage = new LocalFileStorage();
        storage.setBasePath("/oce/repository");

        return storage;
    }


}