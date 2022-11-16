package com.kci.moodstore.pstest.service;

public interface LikeService {

    /**
     * 将redis里的点赞成员数据存入数据库中
     */
    void transLikedMember2DB();

    /**
     * 将redis里的点赞数存入数据库中
     */
    void transLikedCount2DB();

}
