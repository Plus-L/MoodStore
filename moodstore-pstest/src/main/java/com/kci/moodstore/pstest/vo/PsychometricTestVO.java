package com.kci.moodstore.pstest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsychometricTestVO {
    // 主键
    private Long id;
    // 测试名称
    private String title;
    // 概论
    private String conspectus;
    // 题目数量
    private Integer questionNumber;
    // 已测试人数
    private Integer testerNumber;
    // 喜欢人数
    private Integer liked;
    // 测试类型
    private Integer type;
    // 测试封面图片
    private String imageUrl;

    /*
     * 是否点赞过
     */
    private Boolean isLike;
}
