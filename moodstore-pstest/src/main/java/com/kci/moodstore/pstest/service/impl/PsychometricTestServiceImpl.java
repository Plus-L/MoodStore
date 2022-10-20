package com.kci.moodstore.pstest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kci.moodstore.pstest.mapper.PsychometricTestMapper;
import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.service.PsychometricTestService;
import org.springframework.stereotype.Service;

@Service
public class PsychometricTestServiceImpl extends ServiceImpl<PsychometricTestMapper, PsychometricTest>
        implements PsychometricTestService {

}
