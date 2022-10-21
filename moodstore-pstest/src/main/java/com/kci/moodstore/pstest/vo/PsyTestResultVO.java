package com.kci.moodstore.pstest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsyTestResultVO {

    private Long testId;

    private String title;

    private String imgUrl;

    private String result;

}
