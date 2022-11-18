package com.kci.moodstore.pstest.mapstruct;

import com.kci.moodstore.pstest.model.PsychometricTest;
import com.kci.moodstore.pstest.vo.PsyTestResultVO;
import com.kci.moodstore.pstest.vo.PsychometricTestVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PsyTestMapStruct {

    PsyTestMapStruct INSTANCE = Mappers.getMapper(PsyTestMapStruct.class);

    //PsyTestLikedVO map(PsychometricTest value);

    @Mapping(target = "testId", source = "id")
    @Mapping(target = "title", source = "title")
    @Mapping(target = "imageUrl", source = "imageUrl")
    @Mapping(target = "result", ignore = true)
    PsyTestResultVO toResultVO(PsychometricTest test);

    @Mapping(target = "isLike", ignore = true)
    PsychometricTestVO toPsyTestVO(PsychometricTest test);
}
