/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.pojos;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author loc12345
 */
@Entity(name="home")
@Table(name="tblHome")
public class Home {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="paying_reset_date")
    private Date resetDate;
    @Column(name="rent_price")
    private float rentPrice;
    @ManyToOne
    @JoinColumn(name="owner_id")
    private Member owner;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the resetDate
     */
    public Date getResetDate() {
        return resetDate;
    }

    /**
     * @param resetDate the resetDate to set
     */
    public void setResetDate(Date resetDate) {
        this.resetDate = resetDate;
    }

    /**
     * @return the rentPrice
     */
    public float getRentPrice() {
        return rentPrice;
    }

    /**
     * @param rentPrice the rentPrice to set
     */
    public void setRentPrice(float rentPrice) {
        this.rentPrice = rentPrice;
    }

    /**
     * @return the owner
     */
    public Member getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(Member owner) {
        this.owner = owner;
    }
    
    
}
