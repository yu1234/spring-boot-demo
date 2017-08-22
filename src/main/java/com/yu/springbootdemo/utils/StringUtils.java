package com.yu.springbootdemo.utils;

import java.util.concurrent.ThreadLocalRandom;

public class StringUtils {
    /** 产生一个随机的字符串，适用于JDK 1.7 */
    public static String createRandomStr(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append((char) (ThreadLocalRandom.current().nextInt(33, 128)));
        }
        return builder.toString();
    }
}
