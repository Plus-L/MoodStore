package com.kci.moodstore.pstest.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestQuestions {

    private String question;

    private List<TestOptions> options;

}
