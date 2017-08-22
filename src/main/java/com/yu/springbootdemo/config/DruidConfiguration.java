package com.yu.springbootdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.p6spy.engine.spy.P6DataSource;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidConfiguration {
    @Bean
    public DataSource dataSource(@Value("${spring.datasource.url}")
                                         String url,
                                 @Value("${spring.datasource.username}")
                                         String username,
                                 @Value("${spring.datasource.password}")
                                         String password) {

        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(url);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        try {
            //开启druid sql防火墙
            druidDataSource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        P6DataSource p6DataSource=new P6DataSource(druidDataSource);
        return p6DataSource;
    }

    //配置druid监控结果访问相关配置
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean
                = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("resetEnable",
                "true");
        servletRegistrationBean.addInitParameter("loginUsername",
                "druid");
        servletRegistrationBean.addInitParameter("loginPassword",
                "druid");
        servletRegistrationBean.addInitParameter("allow",
                "127.0.0.1");
        return servletRegistrationBean;
    }

    //配置druid监控过滤器
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean
                = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions",
                "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setOrder(5);
        return filterRegistrationBean;
    }

    //添加aop的advice
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    //添加aop的pointcut
    @Bean
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut
                = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPatterns("com.tgb.service.*");
        return jdkRegexpMethodPointcut;
    }

    //设置默认的aop配置对应的是原来的<aop:advisor>
    @Bean
    public Advisor druidAdvisor() {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(jdkRegexpMethodPointcut());
        defaultPointcutAdvisor.setAdvice(druidStatInterceptor());
        return defaultPointcutAdvisor;
    }
}
