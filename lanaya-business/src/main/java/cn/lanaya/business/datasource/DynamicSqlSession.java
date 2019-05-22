package cn.lanaya.business.datasource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class DynamicSqlSession {
    private static final Logger logger = LoggerFactory.getLogger(DynamicSqlSession.class);

    @Autowired
    private ApplicationContext appContext;

    public SqlSession getSqlSession(String name){
        logger.info("getSqlSession - {}", name);
        if(StringUtils.isBlank(name)){
            throw new RuntimeException("name can't be empty");
        }
        SqlSessionFactory bean = appContext.getBean(name, SqlSessionFactory.class);
        return bean.openSession();
    }

    public <T> T getMapper(String sessionName, Class<T> mapperClass){
        return getSqlSession(sessionName).getMapper(mapperClass);
    }
}
