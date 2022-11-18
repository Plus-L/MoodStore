package com.kci.moodstore.pstest.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("mood_user_test_liked")
public class PULRelation {

    private Long userId;

    private Long testId;

}
