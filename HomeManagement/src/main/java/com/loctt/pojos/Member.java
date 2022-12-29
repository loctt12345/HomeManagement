/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 *
 * @author loc12345
 */
@Entity(name="member")
@Table(name="tblMember")
public class Member implements Serializable{
    
    public static String ADMIN = "Chủ nhà";
    
    @Id
    @Column(name="member_id")
    @GeneratedValue(generator = "mem-generator")
    @GenericGenerator(name = "mem-generator", 
      strategy = "com.loctt.pojos.generator.MemberIDGenerator")
    private String id;
    @Column(name="full_name")
    private String fullName;
    @Column(name="home_role")
    private String homeRole;
    @Column(name="room_id")
    private String roomID;
    @Column(name="is_paying")
    private boolean isPaying;
    private String password;
    private String role;
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, 
            cascade = CascadeType.ALL, orphanRemoval =true)
    private List<PayingHistory> listPayingHistory;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the roomID
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * @param roomID the roomID to set
     */
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    /**
     * @return the isPaying
     */
    public boolean isIsPaying() {
        return isPaying;
    }

    /**
     * @param isPaying the isPaying to set
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * @return the listPayingHistory
     */
    public List<PayingHistory> getListPayingHistory() {
        return listPayingHistory;
    }

    /**
     * @param listPayingHistory the listPayingHistory to set
     */
    public void setListPayingHistory(List<PayingHistory> listPayingHistory) {
        this.listPayingHistory = listPayingHistory;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the homeRole
     */
    public String getHomeRole() {
        return homeRole;
    }

    /**
     * @param homeRole the homeRole to set
     */
    public void setHomeRole(String homeRole) {
        this.homeRole = homeRole;
    }
}
