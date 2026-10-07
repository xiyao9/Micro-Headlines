package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.
        NewsHeadLineDao;
import com.atguigu.headline.dao.impl.NewsHeadlineDaoImpl;
import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadLineDao newsHeadLineDao= new NewsHeadlineDaoImpl();
    @Override
    public Map<String, Object> findPage(HeadlineQueryVo headlineQueryVo) {
        // 准备一个map,用于装分页的五项数据
        Map<String,Object> pageInfo =new HashMap<>();
        // 分页查询本页数据
        List<HeadlinePageVo> pageData =newsHeadLineDao.findPageList(headlineQueryVo);
        // 分页查询满足记录的总数据量
        int totalSize = newsHeadLineDao.findPageCount(headlineQueryVo);
        // 页大小
        int pageSize =headlineQueryVo.getPageSize();
        // 总页码数
        int totalPage=totalSize%pageSize == 0 ?  totalSize/pageSize  : totalSize/pageSize+1;
        // 当前页码数
        int pageNum= headlineQueryVo.getPageNum();
        pageInfo.put("pageData",pageData);
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("totalSize",totalSize);


        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        // 修改新闻信息浏览量+1
        newsHeadLineDao.increasePageViews(hid);
        // 查询新闻详情
        return newsHeadLineDao.findHeadlineDetail(hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return newsHeadLineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        return newsHeadLineDao.findHeadlineByHid(hid);
    }

    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        return newsHeadLineDao.updateNewsHeadline(newsHeadline);
    }

    @Override
    public int removeByHid(Integer hid) {
        return newsHeadLineDao.removeByHid(hid);
    }
}
