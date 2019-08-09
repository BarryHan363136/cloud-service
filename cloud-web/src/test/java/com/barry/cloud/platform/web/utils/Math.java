package com.barry.cloud.platform.web.utils;

/**
 * @description:
 * @author: Tongshan.Han
 * @time: 2019/6/26 9:14
 */
public class Math {

    public int cal(){
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        System.out.println(math.cal());
    }

}
