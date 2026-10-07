package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 分页查询头条新闻方法
     * @param headlineQueryVo
     * @return
     */
    Map<String, Object> findPage(HeadlineQueryVo headlineQueryVo);


    /**
     * 根据头条id,显示头条详情
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(Integer hid);



    /**
     * 新增头条
     * @param newsHeadline
     * @return
     */
    int addNewsHeadline(NewsHeadline newsHeadline);


    /**
     * 根据新闻id查询单个新闻
     * @param hid
     * @return
     */
    NewsHeadline findHeadlineByHid(Integer hid);

    int updateNewsHeadline(NewsHeadline newsHeadline);

    int removeByHid(Integer hid);
}
