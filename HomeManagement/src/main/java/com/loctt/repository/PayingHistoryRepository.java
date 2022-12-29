/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.repository;

/**
 *
 * @author loc12345
 */
public interface PayingHistoryRepository {
    public void addNewPaying(String memberID);
    public void removeNewestPaying(String memberID);
    public int getLastRowGroupByMemberID(String memberID);
}
