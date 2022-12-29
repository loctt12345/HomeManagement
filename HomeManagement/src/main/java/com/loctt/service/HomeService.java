/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.service;

import com.loctt.pojos.Home;
import java.util.Date;

/**
 *
 * @author loc12345
 */
public interface HomeService {
    public void resetPaying();
    public void resetPayingAuto(String date);
    public void removePayingAuto();
    public void updatePayingAuto();
    public Date getPayingDate();
}
