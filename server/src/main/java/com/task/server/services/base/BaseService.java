package com.task.server.services.base;

import org.springframework.stereotype.Component;
import lombok.Setter;
import com.task.server.dao.base.BaseDao;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import com.querydsl.jpa.impl.JPAQueryFactory;


@Component
public class BaseService<E, D extends BaseDao<E>>{
    @Setter
    protected D dao;

    @Autowired
    protected EntityManager entityManager;

    // Factory class for query and DML clause creation
    // @Autowired
    // protected JPAQueryFactory queryFactory;

    public List<E> findAll() {
        return dao.findAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        dao.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletes(Long[] ids) {
        for (long id : ids) {
            delete(id);
        }
    } 

    public E save(E e) {
        return (E) dao.save(e); 
    }

    public long count(){
        return dao.count();
    }
}
