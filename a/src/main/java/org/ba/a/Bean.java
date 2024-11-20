package org.ba.a;


import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;


@Singleton
public class Bean {
    private static int count = 0;
    private static long start = System.currentTimeMillis();

    @Schedule(hour = "*", minute = "*", second = "*/6", persistent = false)
    private void task() {
        long s = (System.currentTimeMillis()-start)/1000;

        String html = JsoupProvider.getString();
        System.out.printf("%d:%02d, iteration %d, %s\n", s/60, s%60, ++count, html);
    }

    public static void main(String[] args) {
        new Bean().task();
    }
}
