/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.repository;

import com.loctt.pojos.Member;
import com.loctt.pojos.PayingHistory;
import java.util.List;

/**
 *
 * @author loc12345
 */
public interface MemberRepository {
    public List<Member> getList(String kw, int page);
    public List<Member> getListById(String id);
    public void setIsPayingById(String id, boolean isPaying);
    public List<PayingHistory> getHistory(String id);
    public void setAllIsPaying(boolean status);
    public void addNewMember(String fullName, String role, String roomID);
    public void updateMember(Member member);
    public void deleteMember(String id);
    public int countMember();
    public void setPassword(String id, String newPassword);
}
