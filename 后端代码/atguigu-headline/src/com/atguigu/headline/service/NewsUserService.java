package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     *这是根据登录的账户查询单个用户的方法
     * @param
     * @return  成功了返回NewsUser对象 失败返回空
     */
    NewsUser findByUsername(String username);

    /**
     * 根据token查找用户信息的方法
     * @param userId
     * @return 返回的是一个NewsUser对象
     */
    NewsUser findByUid(Integer userId);

    /**
     * 实现注册业务的方法
     * @param newsUser
     */
    int regist(NewsUser newsUser);
}
