/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.service;

import com.loctt.pojos.Member;
import com.loctt.pojos.PayingHistory;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author loc12345
 */
public interface MemberService extends UserDetailsService{
    public List<Member> getMemberList(String kw, int page);
    public Member getMemberById(String id);
    public void confirmPayingThisMonth(String id);
    public void cancelPayingThisMonth(String id);
    public List<PayingHistory> getPayingHistoryById(String id);
    public void addNewMember(String fullName, String role, String roomID);
    public void updateMember(Member member);
    public void deleteMember(String id);
    public int countMember();
    public void changePassword(String id, String password);
}
