package com.kci.moodstore.pstest.controller;

import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.service.PsyTestResultService;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/psyTestResult")
public class PsyTestResultController {

    @Resource
    private PsyTestResultService resultService;

    @GetMapping("/{testId}/{Item}")
    public CommonResult<PsyTestResultVO> getResultByItem
            (@PathVariable Long testId, @PathVariable String Item, HttpServletRequest request) {
        // 我预想是用户存在redis里，从token中提取，不晓得嘉豪哥咋想
        String token = request.getHeader("Authorization");
        Long userId = 2L;  // TODO 先意思意思
        return resultService.getResultByItem(userId, testId, Item);
    }

}
