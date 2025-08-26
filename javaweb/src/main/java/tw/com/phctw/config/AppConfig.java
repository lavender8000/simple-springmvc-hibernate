package tw.com.phctw.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement   // 啟用 spring 的註解式事務管理
@ComponentScan(basePackages = {"tw.com.phctw.service", "tw.com.phctw.repository"})
public class AppConfig {

    // Driver 配置、連接池配置
    @Bean
    public DataSource dataSource() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("[OracleDriver] Class.forName 找不到 OracleDriver Class", e);
        }

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("DBO");
        dataSource.setPassword("123456");
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(2);
        dataSource.setIdleTimeout(30000);
        return dataSource;
    }

    // Hibernate 配置 (EntityManager 是 Hibernate 操作 Entity 與資料庫的核心 API)
    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        // 掃描 model 包 (Entity)
        em.setPackagesToScan("tw.com.phctw.model");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        Properties jpaProps = new Properties();
        jpaProps.put("hibernate.show_sql", "true");
        jpaProps.put("hibernate.hbm2ddl.auto", "validate");
        em.setJpaProperties(jpaProps);
        em.afterPropertiesSet();
        return em.getObject();
    }

    // 啟用事務管理
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    // Jakarta Mail 配置
    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        // TODO: 你申請的 smtp gmail 帳號
        mailSender.setUsername("yourGmail@gmail.com");
        // TODO: 你申請的 smtp gmial 應用程式密碼
        mailSender.setPassword("yourGmailApplicationPasswrod");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

}
