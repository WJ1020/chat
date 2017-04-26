package com.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:config/source.properties"})
public class BeanConfig {

    @Autowired
    private Environment env;
    @Bean(destroyMethod = "close")
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(env.getProperty("source.driverClassName").trim());
        dataSource.setUrl(env.getProperty("source.url").trim());
        dataSource.setUsername(env.getProperty("source.username").trim());
        dataSource.setPassword(env.getProperty("source.password").trim());
        try {
            dataSource.setFilters(env.getProperty("source.filters").trim());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){

        ServletRegistrationBean servletRegistrationBean=new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        servletRegistrationBean.addInitParameter("loginUsername","admin");

        servletRegistrationBean.addInitParameter("loginPassword","admin1020");

        servletRegistrationBean.addInitParameter("resetEnable","false");

        return servletRegistrationBean;

    }
    @Bean
    public FilterRegistrationBean druidStatFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean(new WebStatFilter());

        filterRegistrationBean.addUrlPatterns("/*");

        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return filterRegistrationBean;
    }
}
