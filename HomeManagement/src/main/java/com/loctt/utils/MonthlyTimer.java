/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loctt.utils;

/**
 *
 * @author loc12345
 */
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.Calendar;

public class MonthlyTimer { 
    // What to do
    private final Runnable whatToDo;

    // when 
    private final int dayOfMonth;
    private final int hourOfDay;

    // The current timer
    private Timer current = new Timer();//to avoid NPE

    public void cancelCurrent() { 
        current.cancel();// cancel this execution;
        current.purge(); // removes the timertask so it can be gc'ed
    }

    // create a new instance
    public static MonthlyTimer schedule( Runnable runnable, int dayOfMonth, int hourOfDay ) { 
        return new MonthlyTimer( runnable, dayOfMonth, hourOfDay );
    }

    private MonthlyTimer(Runnable runnable, int day, int hour ) { 
        this.whatToDo = runnable;
        this.dayOfMonth = day;
        this.hourOfDay = hour;
        schedule();
    }
    // Schedules the task for execution on next month. 
    private void schedule() { 
        // Do you mean like this?
        cancelCurrent();
        current = new Timer(); // assigning a new instance
        // will allow the previous Timer to be gc'ed

        current.schedule( new TimerTask() { 
            public void run() { 
                try { 
                    whatToDo.run();
                } finally { 
                    schedule();// schedule for the next month
                }
            }
        } , TimeHelper.getNextMonth(dayOfMonth, hourOfDay));           
    }
    // Do the next date stuff
//    private Date nextDate() { 
//        Calendar runDate = Calendar.getInstance();
//        runDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        runDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        runDate.set(Calendar.MINUTE, 0);
//        if ((new Date()).getDate() >= dayOfMonth)
//            runDate.add(Calendar.MONTH, 1);//set to next month
//        System.out.println(runDate.getTime());
//        return runDate.getTime();
//    }
    
//    public static void main( String [] args ) { 
//        int the1st = 25;
//        int at16hrs = 21;
//
//        MonthlyTimer t = MonthlyTimer.schedule( new Runnable() { 
//            public void run() { 
//                System.out.println( "Hola" );
//            }}, the1st, at16hrs );
//
//        // will print "Hola" every 1st at 16:00 hrs.
//       // if needed you can cancel with: 
//        //t.cancelCurrent();
//
//    }
}
//
//class UseIt { 
//    public static void main( String [] args ) { 
//        int the1st = 1;
//        int at16hrs = 16;
//
//        MonthlyTimer t = MonthlyTimer.schedule( new Runnable() { 
//            public void run() { 
//                System.out.println( "Hola" );
//            }}, the1st, at16hrs );
//
//        // will print "Hola" every 1st at 16:00 hrs.
//       // if needed you can cancel with: 
//        t.cancelCurrent();
//
//    }
//}
//   
