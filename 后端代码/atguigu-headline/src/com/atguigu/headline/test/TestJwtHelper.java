package com.atguigu.headline.test;

import com.atguigu.headline.util.JwtHelper;
import org.testng.annotations.Test;

public class TestJwtHelper {

    @Test
    public void testAllMethod(){
        String token = JwtHelper.createToken(1L);
        System.out.println(token);

        Long userId = JwtHelper.getUserId(token);
        System.out.println(userId);

        System.out.println(JwtHelper.isExpiration(token));

    }
}
