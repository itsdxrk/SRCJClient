package me.itsdxrk.srcjclient.util;

import java.util.concurrent.TimeUnit;

public class TimeFormat {

    public String formatTime(double time) {
        String millis = String.valueOf(time);
        millis = millis.substring(millis.indexOf(".") + 1);
        long intTime = (long) Math.floor(time);
        long minutes = TimeUnit.SECONDS.toMinutes(intTime);
        long seconds = TimeUnit.SECONDS.toSeconds(intTime) % 60;
        String totalTime = String.format("%02d:%02d." + millis, minutes, seconds);
        return totalTime;
    }
}
