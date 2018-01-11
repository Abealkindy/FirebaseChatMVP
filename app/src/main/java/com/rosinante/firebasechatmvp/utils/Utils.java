package com.rosinante.firebasechatmvp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Rosinante24 on 11/01/18.
 */

public class Utils {
    public static String EXTRA_ROOM_NAME = "extra_room_name";

    public static String convertTime(long timeStamp) {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date(timeStamp);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);
    }
}
