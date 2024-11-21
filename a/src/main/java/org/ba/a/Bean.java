package org.ba.a;


import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;

import java.util.List;

import static org.ba.b.DBProvider.getPrinters;


@Singleton
public class Bean {
    private static int count = 0;
    private static final long start = System.currentTimeMillis();

    @Schedule(hour = "*", minute = "*", second = "*/6", persistent = false)
    private void task() {
        long s = (System.currentTimeMillis()-start)/1000;

        String html = JsoupProvider.getString();
        List<String> printers = getPrinters();
        System.out.printf("%d:%02d, iteration %d, %s\n%s\n", s/60, s%60, ++count, html, printers.toString());
    }

    public static void main(String[] args) {
        new Bean().task();
    }
}
