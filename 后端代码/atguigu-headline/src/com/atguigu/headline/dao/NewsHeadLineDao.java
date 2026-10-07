package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadLineDao {
    /**
     * 根据查询条件,查询当前页数据
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);


    /**
     * 根据查询条件,查询满足条件的记录数
     * @param headlineQueryVo
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);


    /**
     * 多表查询新闻详情
     *
     * @param hid
     * @return
     */
    int increasePageViews(Integer hid);

    HeadlineDetailVo findHeadlineDetail(Integer hid);



    /**
     * 头条存入数据库
     * @param newsHeadline
     * @return
     */
    int addNewsHeadline(NewsHeadline newsHeadline);

    NewsHeadline findHeadlineByHid(Integer hid);

    int updateNewsHeadline(NewsHeadline newsHeadline);

    int removeByHid(Integer hid);
}
