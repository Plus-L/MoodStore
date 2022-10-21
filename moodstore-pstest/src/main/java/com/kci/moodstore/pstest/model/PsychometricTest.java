package com.kci.moodstore.pstest.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mood_psychometric_test")
public class PsychometricTest implements Serializable {

    private static final long serialVersionUID = 1L;

    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
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
    // 测试类型(有8种)
    private Integer type;
    // 测试封面图片
    private String imageUrl;
    // 状态
    private Integer status;
    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    // 修改时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
