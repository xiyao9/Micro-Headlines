package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.NewsUserDao;
import com.atguigu.headline.dao.impl.NewsUserDaoImpl;
import com.atguigu.headline.pojo.NewsUser;
import com.atguigu.headline.service.NewsUserService;
import com.atguigu.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService {
private NewsUserDao newsUserDao=new NewsUserDaoImpl();

    @Override
    public NewsUser findByUsername(String username) {
        return newsUserDao.findByUsername(username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return newsUserDao.findByUid(userId);
    }

    @Override
    public int regist(NewsUser newsUser) {
        //密码明文转密文
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        return newsUserDao.regist(newsUser);
    }
}
