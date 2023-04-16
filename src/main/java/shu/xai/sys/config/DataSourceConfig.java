package shu.xai.sys.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by yuziyi on 2021/6/19.
 */
@Configuration
public class DataSourceConfig {

    //数据源1
    @Primary
    @Bean("dataSourceXAI")
    @ConfigurationProperties("spring.datasource.druid.xai")
    public DataSource mDataSourceOne(){
        return DruidDataSourceBuilder.create().build();
    }


    @Primary
    @Bean("jdbcTemplateXAI")
    public JdbcTemplate mJdbcTemplateOne(@Qualifier("dataSourceXAI") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    // 多数据源下的事务管理
    @Primary
    @Bean(name = "TransactionManagerXAI")
    public PlatformTransactionManager TransactionManagerXAI(@Qualifier("dataSourceXAI") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    //数据源2
    @Bean("dataSourcesp")
    @ConfigurationProperties("spring.datasource.druid.sp")
    public DataSource mDataSourceTwo(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean("jdbcTemplatesp")
    public JdbcTemplate mJdbcTemplateTwo(@Qualifier("dataSourcesp") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


    @Bean(name = "TransactionManagersp")
    public PlatformTransactionManager TransactionManagersp(@Qualifier("dataSourcesp") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}