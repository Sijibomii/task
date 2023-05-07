package com.task.server.config;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.PersistenceUnit;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;


// query factory config for dsl
@Configuration
public class QueryDslConfig {
    
    // @PersistenceUnit
    // EntityManager entityManager;

    // @Bean
    // public JPAQueryFactory getJPAQueryFactory(EntityManager entityManager){
    //     return new JPAQueryFactory(entityManager);
    // }
}

