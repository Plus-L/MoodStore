package com.kci.moodstore.pstest.bo;

import com.kci.moodstore.pstest.service.LikeService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class LikeTask extends QuartzJobBean {

    @Resource
    private LikeService likeService;

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(@NonNull JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("likeTask------------ {}", sdf.format(new Date()));

        // 将redis中的数据同步到数据库中
        likeService.transLikedMember2DB();
        likeService.transLikedCount2DB();
        likeService.transTesterCount2DB();
    }
}
