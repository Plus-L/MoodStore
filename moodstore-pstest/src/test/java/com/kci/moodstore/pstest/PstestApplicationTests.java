package com.kci.moodstore.pstest;

import com.kci.moodstore.pstest.mapper.UserTestLikedMapper;
import com.kci.moodstore.pstest.mapstruct.PsyTestMapStruct;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.service.PsychometricTestService;
import com.kci.moodstore.pstest.vo.PsyTestLikedVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PstestApplicationTests {

    @Resource
    private PsychometricTestService psychometricTestService;

    @Resource
    private UserTestLikedMapper likedMapper;

    @Test
    void insert() {
        PsychometricTest test = new PsychometricTest();
        psychometricTestService.save(test);
    }

    @Test
    void getByLiked() {
        List<PsychometricTest> testList = likedMapper.getByLiked(1L);
        List<PsyTestLikedVO> testLikedVOList = PsyTestMapStruct.INSTANCE.toLikedVOList(testList);
        for (PsyTestLikedVO test: testLikedVOList) {
            System.out.println(test);
        }
    }

}
