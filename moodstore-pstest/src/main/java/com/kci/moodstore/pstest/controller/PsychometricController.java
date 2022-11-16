package com.kci.moodstore.pstest.controller;

import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.service.PsychometricTestService;
import com.kci.moodstore.pstest.vo.PsychometricTestVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/psychometric")
public class PsychometricController {

    @Resource
    private PsychometricTestService testService;

    /**
     * 用户点击心理测试封面后得到的心理测试详情
     * @param testId 心理测试ID
     * @return 心理测试VO
     */
    @GetMapping("/{testId}")
    public CommonResult<PsychometricTestVO> getPsyTestDetails(@PathVariable Long testId) {
        return testService.getPsyTestDetails(testId);
    }

    /**
     * 用户点赞
     * @param testId 心理测试ID
     * @return true 点赞成功，false 取消点赞
     */
    @GetMapping("/favor/{testId}")
    public CommonResult<Boolean> liked(@PathVariable Long testId) {
        return testService.liked(12L, testId);
    }

    /**
     * 获取点赞收藏的心理测试
     * @return List
     */
    @GetMapping("/favorTests")
    public CommonResult<List<PsychometricTestVO>> getByLiked() {
        return testService.getByLiked(12L);
    }

}
