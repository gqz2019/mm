package com.gqz.wx;

import com.gqz.wx.utils.LocationUtil;
import org.junit.Test;

/**
 * <p>测试</p>
 *
 * @author gqz20
 * @create 2021-04-23 21:57
 **/
public class test {
    @Test
    public void test1(){
        String s = LocationUtil.parseLocation("38.76623,112.43213");
        System.out.println(s);
    }
}
