/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.repository.impl;

import com.loctt.pojos.Member;
import com.loctt.pojos.PayingHistory;
import com.loctt.repository.MemberRepository;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author loc12345
 */
@Repository
public class MemberRepositoryImpl implements MemberRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    @Transactional
    public List<Member> getList(String kw, int page) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM member WHERE fullName like :kw");
        q.setParameter("kw", "%" + kw + "%");
        int maxPage = 6;
        q.setMaxResults(maxPage);
        q.setFirstResult(Math.max((page - 1) * maxPage, 0));
        return q.getResultList();
    }
    
    @Override
    @Transactional
    public List<Member> getListById(String id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM member WHERE id = :id");
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    @Override
    @Transactional
    public void setIsPayingById(String id, boolean isPaying) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("UPDATE member set isPaying = :isPaying "
                + "WHERE id = :id");
        q.setParameter("id", id);
        q.setParameter("isPaying", isPaying);
        q.executeUpdate();
    }
    
    @Override
    @Transactional
    public List<PayingHistory> getHistory(String id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Member m = s.get(Member.class, id);
        return m.getListPayingHistory();
    }
    
    @Override
    @Transactional
    public void setAllIsPaying(boolean status) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("UPDATE member set isPaying = :status");
        q.setParameter("status", status);
        q.executeUpdate();
    }
    
    @Override
    @Transactional
    public void addNewMember(String fullName, String role, String roomID) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Member mem = new Member();
        mem.setFullName(fullName);
        mem.setHomeRole(role);
        mem.setRoomID(roomID);
        mem.setIsPaying(false);
        mem.setPassword(this.passwordEncoder.encode("12345"));
        mem.setRole("user");
        s.save(mem);
    }
    
    @Override
    @Transactional
    public void updateMember(Member member) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Member oldMember = s.get(Member.class, member.getId());
        oldMember.setFullName(member.getFullName());
        oldMember.setHomeRole(member.getHomeRole());
        oldMember.setRoomID(member.getRoomID());
        s.save(oldMember);
    }
    
    @Override
    @Transactional
    public void deleteMember(String id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("DELETE FROM member "
                + "WHERE id = :id");
        q.setParameter("id", id);
        q.executeUpdate();
    }
    
    @Override
    @Transactional
    public int countMember() {
        Session s = sessionFactory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM member");
        return Integer.parseInt(q.getSingleResult().toString());
    }
    
    @Override
    @Transactional
    public void setPassword(String id, String password) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Member oldMember = s.get(Member.class, id);
        oldMember.setPassword(this.passwordEncoder.encode(password));
        s.save(oldMember);
    }
}
