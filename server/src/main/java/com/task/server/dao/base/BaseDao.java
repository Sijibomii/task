package com.task.server.dao.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;


// base data access obj (repository)
@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T,Long>,JpaSpecificationExecutor<T>,QuerydslPredicateExecutor<T> {
}