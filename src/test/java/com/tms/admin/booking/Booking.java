package com.tms.admin.booking;

import com.tms.base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Booking extends BaseTest {

    @BeforeMethod
    public void setUp(){
        initialization();
    }

    @Test
    public void test(){
        System.out.println("hello");
    }
}
