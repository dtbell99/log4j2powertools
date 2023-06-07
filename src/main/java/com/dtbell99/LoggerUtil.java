package com.dtbell99;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    Logger log = LogManager.getLogger();

    public LoggerUtil() {

    }

    public void info(String message) {
        log.info(message);
    }
}
