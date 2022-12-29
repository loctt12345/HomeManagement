/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.service.impl;

import com.loctt.pojos.Home;
import com.loctt.repository.HomeRepository;
import com.loctt.repository.MemberRepository;
import com.loctt.service.HomeService;
import com.loctt.utils.MonthlyTimer;
import com.loctt.utils.TimeHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loc12345
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HomeRepository homeRepository;

    @Override
    public void resetPaying() {
        this.memberRepository.setAllIsPaying(false);
    }

    @Override
    public void resetPayingAuto(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException ex) {
            //Logger.getLogger(HomeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (parsed == null) {
            return;
        }
        
        this.homeRepository.setPayingDate(
                TimeHelper.getNextMonth(parsed.getDate(), 0), 1);

        int day = parsed.getDate();
        int hour = 0;

        MonthlyTimer timer = MonthlyTimer.schedule(new Runnable() {
            public void run() {
                memberRepository.setAllIsPaying(false);
                updatePayingAuto();
            }
        }, day, hour);
    }

    @Override
    public void removePayingAuto() {
        this.homeRepository.setPayingDate(null, 1);
    }

    @Override
    public void updatePayingAuto() {
        Home home = this.homeRepository.getHome(1);
        Calendar date = Calendar.getInstance();
        date.setTime(home.getResetDate());
        date.add(Calendar.MONTH, 1);
        this.homeRepository.setPayingDate(date.getTime(), 1);
    }
    
    public Date getPayingDate() {
        Home home = this.homeRepository.getHome(1);
        return home.getResetDate();
    }
}
