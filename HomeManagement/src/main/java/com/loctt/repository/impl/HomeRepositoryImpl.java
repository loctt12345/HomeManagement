/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.repository.impl;

import com.loctt.pojos.Home;
import com.loctt.pojos.Member;
import com.loctt.repository.HomeRepository;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author loc12345
 */
@Repository
public class HomeRepositoryImpl implements HomeRepository {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    @Transactional
    public void setPayingDate(Date date, int id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Home home = s.get(Home.class, id);
        home.setResetDate(date);
        s.save(home);
    }
    @Override
    @Transactional
    public Home getHome(int id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        return s.get(Home.class, id);
    }
}
