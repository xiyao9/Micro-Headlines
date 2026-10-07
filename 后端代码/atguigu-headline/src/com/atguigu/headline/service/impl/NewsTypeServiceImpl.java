package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.NewsTypeDao;
import com.atguigu.headline.dao.impl.NewsTypeDaoImpl;
import com.atguigu.headline.pojo.NewsType;
import com.atguigu.headline.service.NewsTpyeService;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTpyeService {
    private NewsTypeDao typeDao = new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return typeDao.findAll();
    }
}
