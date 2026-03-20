package com.sqllite.sqlliteproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author kangchen
 * @date 2026/3/19 11:49
 */
@Component
public class CronExecConfig {
    private Logger logger = LoggerFactory.getLogger(CronExecConfig.class);
    @Scheduled(cron = "*/2 * * * * ?")
    public void method01() {
        String[] exec = {"/bin/echo","$(date)"};
        try {
            Process exec1 = Runtime.getRuntime().exec(exec);
            logger.info("cron...{}", new String(exec1.getInputStream().readAllBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
