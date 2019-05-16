package cn.lanaya.business.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class DatasourceConfig {

    @Bean(name = "primaryDatasource")
    @Qualifier("primaryDatasource")
    @ConfigurationProperties(prefix = "primary.datasource")
    public DruidDataSource primaryDatasource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "primarySqlSessionFactory")
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDatasource")
            DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/**/*Mapper.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

    @Bean(name = "backupDatasource")
    @Qualifier("backupDatasource")
    @ConfigurationProperties(prefix = "backup.datasource")
    public DruidDataSource backupDatasource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "backupSqlSessionFactory")
    public SqlSessionFactory backupSqlSessionFactory(@Qualifier("backupDatasource")
                                                              DruidDataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/mapper/**/*Mapper.xml");
        bean.setMapperLocations(resources);
        return bean.getObject();
    }

}
