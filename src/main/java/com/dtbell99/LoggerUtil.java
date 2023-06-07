package com.dtbell99;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.lambda.powertools.logging.LoggingUtils;

public class LoggerUtil {
    Logger log = LogManager.getLogger();

    public LoggerUtil() {
        LoggingUtils.appendKey("name", "david");
    }

    public void addContext(String name, String value) {
        LoggingUtils.appendKey(name, value);
    }

    public void info(String message) {
        log.info(message);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void error(String message) {
        log.error(message);
    }

    public void debug(String message) {
        log.debug(message);
    }
}
