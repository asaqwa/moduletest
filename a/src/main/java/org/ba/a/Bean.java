package org.ba.bean;

import org.ba.commons.Util;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class Bean {

    @Schedule(hour = "*", minute = "*", second = "0", persistent = false)
    private void task() {
        try {
        String result = Util.getString();
        System.out.println(result);
        } catch (Throwable e) {
            System.out.println("ERROR, " + e.toString());
        }
    }
    
    public static void main(String[] args) {
        new Bean().task();
    }
}
