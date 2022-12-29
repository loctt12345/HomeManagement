/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.utils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author loc12345
 */
public class TimeHelper {
    public static Date getNextMonth(int day, int hour) {
        Calendar runDate = Calendar.getInstance();
        runDate.set(Calendar.DAY_OF_MONTH, day);
        runDate.set(Calendar.HOUR_OF_DAY, hour);
        runDate.set(Calendar.MINUTE, 0);
        if ((new Date()).getDate() >= day)
            runDate.add(Calendar.MONTH, 1);//set to next month
        System.out.println("Set auto time at: " + runDate.getTime());
        return runDate.getTime();
    }
}
