/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.repository;

import com.loctt.pojos.Home;
import java.util.Date;

/**
 *
 * @author loc12345
 */
public interface HomeRepository {
    public void setPayingDate(Date date, int id);
    public Home getHome(int id);
}
