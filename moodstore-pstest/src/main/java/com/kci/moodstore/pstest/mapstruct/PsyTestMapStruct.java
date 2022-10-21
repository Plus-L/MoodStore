package com.kci.moodstore.pstest.mapstruct;

import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.vo.PsyTestLikedVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PsyTestMapStruct {

    PsyTestMapStruct INSTANCE = Mappers.getMapper(PsyTestMapStruct.class);

    List<PsyTestLikedVO> toLikedVOList(List<PsychometricTest> testList);

}
