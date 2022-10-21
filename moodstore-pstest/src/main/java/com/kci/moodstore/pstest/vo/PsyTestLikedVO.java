package com.kci.moodstore.pstest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PsyTestLikedVO {

    private Long id;

    private String title;

    private String conspectus;

    private Integer testerNumber;

    private String imageUrl;

}
