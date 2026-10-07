package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsUser;

public interface NewsUserDao {
    /**
     *这是根据登录的账户查询单个用户的方法
     * @param
     * @return  成功了返回NewsUser对象 失败返回空
     */


    NewsUser findByUsername(String username);

    /**
     * 这是根据用户id查找用户信息的方法
     * @param userId
     * @return 返回的是一个用户对象
     */
    NewsUser findByUid(Integer userId);


    /**
     * 这是注册业务的方法
     *
     * @param newsUser
     * @return
     */
    int regist(NewsUser newsUser);
}
