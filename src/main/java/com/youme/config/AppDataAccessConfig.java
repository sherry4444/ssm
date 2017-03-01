package com.youme.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@PropertySource("classpath:persistence.properties")
@EnableTransactionManagement
@MapperScan("com.youme.*")
public class AppDataAccessConfig {
	private static final Logger logger = LoggerFactory.getLogger(AppDataAccessConfig.class);

  /*  @Value("${dataSource.driverClassName}")
    private static String driverClassName ;
    @Value("${dataSource.url}")
    private static String url;
    @Value("${dataSource.username}")
    private static String username ;
    @Value("${dataSource.password}")
    private static String password;*/

	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8";
	private static final String username = "root";
	private static final String password = "";

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		logger.debug("jdbc.url is:" + url);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxTotal(50);
		dataSource.setMinIdle(5);
		dataSource.setMaxIdle(10);
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		return sessionFactory.getObject();
	}
}
