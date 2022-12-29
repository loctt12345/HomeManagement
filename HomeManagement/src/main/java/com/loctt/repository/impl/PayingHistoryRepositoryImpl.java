/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.repository.impl;

import com.loctt.pojos.Member;
import com.loctt.pojos.PayingHistory;
import com.loctt.repository.MemberRepository;
import com.loctt.repository.PayingHistoryRepository;
import java.util.Date;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author loc12345
 */
@Repository
public class PayingHistoryRepositoryImpl implements PayingHistoryRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
   
    @Override
    @Transactional
    public void addNewPaying(String memberID) {
        Session s = sessionFactory.getObject().getCurrentSession();
        
        Member member = s.get(Member.class, memberID);
        
        PayingHistory paying = new PayingHistory();
        paying.setId(0);
        paying.setMember(member);
        paying.setPayingDate(new Date());
        
        s.save(paying);
    }
    
    @Override
    @Transactional
    public void removeNewestPaying(String memberID) {
        Session s = sessionFactory.getObject().getCurrentSession();
        int newestRowID = getLastRowGroupByMemberID(memberID);
        Query q = s.createQuery("DELETE FROM payingHistory "
                + "WHERE id=:id");
        q.setParameter("id", newestRowID);
        q.executeUpdate();
    }
    @Override
    @Transactional
    public int getLastRowGroupByMemberID(String memberID) {
        Session s = sessionFactory.getObject().getCurrentSession();
       
        Query q = s.createQuery("SELECT MAX(a.id) FROM payingHistory as a "
                + "WHERE a.member.id = :id");
        q.setParameter("id", memberID);
        return (int) q.getResultList().get(0);
    }

}
