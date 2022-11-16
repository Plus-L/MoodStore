package com.kci.moodstore.pstest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kci.moodstore.framework.common.result.CommonResult;
import com.kci.moodstore.pstest.model.PsyTestResult;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;

public interface PsyTestResultService extends IService<PsyTestResult> {

    /**
     * 通过前端传来的分数返回一个测试结果并且将结果插入数据库和保存到redis中，会更新答案
     * @param userId 用户id
     * @param testId 测试id
     * @param Item   要哪个答案前端直接传好一点，因为后端不能写死了。只能前端灵活一点，在前端就判好分数啥的直接拿答案。
     * @return  PsyTestResultVO
     */
    CommonResult<PsyTestResultVO> getResultByItem(Long userId, Long testId, String Item);

    /**
     * 查询redis中是否有结果的缓存，有则直接返回。无则从数据库查询并保存到redis中，仅仅是查看
     * @param userId 用户Id
     * @param testId 测试id
     * @return PsyTestResultVO
     */
    CommonResult<PsyTestResultVO> getResult(Long userId, Long testId);

}
