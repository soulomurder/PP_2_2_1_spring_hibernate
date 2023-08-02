package hiber.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan(value = "hiber")
public class AppConfig {

   @Autowired
   private DataSource dataSource;

   @Bean
   public EntityManagerFactory entityManagerFactory() {
      LocalContainerEntityManagerFactoryBean emFactoryBean = new LocalContainerEntityManagerFactoryBean();
      emFactoryBean.setPersistenceUnitName("myPersistenceUnit");
      emFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
      emFactoryBean.afterPropertiesSet();

      return emFactoryBean.getObject();
   }

   @Bean
   public EntityManager entityManager() {
      return entityManagerFactory().createEntityManager();
   }

   @Bean
   public JpaTransactionManager transactionManager() {
      JpaTransactionManager transactionManager = new JpaTransactionManager();
      transactionManager.setEntityManagerFactory(entityManagerFactory());
      return transactionManager;
   }

   @Bean
   public DataSource dataSource() {
      DriverManagerDataSource dataSource = new DriverManagerDataSource();
      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource.setUrl("jdbc:mysql://localhost:3306/myscheme");
      dataSource.setUsername("root");
      dataSource.setPassword("GlowStone:89");
      return dataSource;
   }
}
