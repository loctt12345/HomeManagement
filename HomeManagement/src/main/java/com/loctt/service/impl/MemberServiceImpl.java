/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.service.impl;

import com.loctt.pojos.Member;
import com.loctt.pojos.PayingHistory;
import com.loctt.repository.MemberRepository;
import com.loctt.repository.PayingHistoryRepository;
import com.loctt.service.MemberService;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author loc12345
 */
@Service("userDetailsService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PayingHistoryRepository payingHistoryRepository;

    private Timer timer;

    @Override
    public List<Member> getMemberList(String kw, int page) {
        return memberRepository.getList(kw, page);
    }

    @Override
    public Member getMemberById(String id) {
        List<Member> list = this.memberRepository.getListById(id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public void confirmPayingThisMonth(String id) {
        this.memberRepository.setIsPayingById(id, true);
        this.payingHistoryRepository.addNewPaying(id);
    }

    @Override
    public void cancelPayingThisMonth(String id) {
        this.memberRepository.setIsPayingById(id, false);
        this.payingHistoryRepository.removeNewestPaying(id);
    }

    @Override
    public List<PayingHistory> getPayingHistoryById(String id) {
        List<PayingHistory> list = this.memberRepository.getHistory(id);
        return list.subList(Math.max(0, list.size() - 3), list.size());
    }

    @Override
    public void addNewMember(String fullName, String role, String roomID) {
        this.memberRepository.addNewMember(fullName, role, roomID);
    }

    @Override
    public void updateMember(Member member) {
        this.memberRepository.updateMember(member);
    }

    @Override
    public void deleteMember(String id) {
        this.memberRepository.deleteMember(id);
    }
    
    @Override
    public int countMember() {
        return this.memberRepository.countMember();
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = this.getMemberById(username);
        if (member == null) {
            throw new UsernameNotFoundException("Không tồn tại id");
        }
        
        Set<GrantedAuthority> auth = new HashSet<>();
        
        auth.add(new SimpleGrantedAuthority(member.getRole()));
        return new User(member.getId(), member.getPassword(), auth);
    }
    
    public void changePassword(String id, String password) {
        this.memberRepository.setPassword(id, password);
    }
}
