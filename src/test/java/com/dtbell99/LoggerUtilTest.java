package com.dtbell99;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class LoggerUtilTest {

    //private final PrintStream standardOut = System.out;
    private final static ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterAll
    public static void writeOutput() {
        try (OutputStream outputStream = new FileOutputStream("logoutput.json")) {
            outputStreamCaptor.writeTo(outputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void whenInfoMethodExecuted_thenCorrectOutputProduced() {
        LoggerUtil logger = new LoggerUtil();
        logger.info("Info Message");
        String output = outputStreamCaptor.toString()
                .trim();

        Assertions.assertTrue(output.contains("\"level\":\"INFO\",\"loggerName\":\"com.dtbell99.LoggerUtil\",\"message\":\"Info Message\""));
    }

    @Test
    public void whenWarnMethodExecuted_thenCorrectOutputProduced() {
        LoggerUtil logger = new LoggerUtil();
        logger.warn("Warn Message");
        String output = outputStreamCaptor.toString()
                .trim();
        Assertions.assertTrue(output.contains("\"level\":\"WARN\",\"loggerName\":\"com.dtbell99.LoggerUtil\",\"message\":\"Warn Message\""));
    }

    @Test
    public void whenDebugMethodExecuted_thenCorrectOutputProduced() {
        LoggerUtil logger = new LoggerUtil();
        logger.debug("Debug Message");
        String output = outputStreamCaptor.toString()
                .trim();
        Assertions.assertTrue(output.contains("\"level\":\"DEBUG\",\"loggerName\":\"com.dtbell99.LoggerUtil\",\"message\":\"Debug Message\""));
    }

    @Test
    public void whenErrorMethodExecuted_thenCorrectOutputProduced() {
        LoggerUtil logger = new LoggerUtil();
        logger.error("Error Message");
        String output = outputStreamCaptor.toString()
                .trim();
        Assertions.assertTrue(output.contains("\"level\":\"ERROR\",\"loggerName\":\"com.dtbell99.LoggerUtil\",\"message\":\"Error Message\""));
    }

    @Test
    public void whenContextIsAdded_thenCorrectOutputProduced() {
        LoggerUtil logger = new LoggerUtil();
        logger.addContext("username", "dave");
        logger.info("Test Context");
        String output = outputStreamCaptor.toString()
                .trim();
        Assertions.assertTrue(output.contains("\"username\":\"dave\""));
    }

    @Test
    public void whenConstructorExecutes_thenLogContainsCorrectFields() {
        LoggerUtil logger = new LoggerUtil();
        logger.info("Test Context Environment Variables");
        String output = outputStreamCaptor.toString()
                .trim();
        Assertions.assertTrue(output.contains("\"ENVIRONMENT\":"));
        Assertions.assertTrue(output.contains("\"LOG_LEVEL\":"));
        Assertions.assertTrue(output.contains("\"SERVICE_NAME\":"));
        Assertions.assertTrue(output.contains("\"boundedContext\":"));
        Assertions.assertTrue(output.contains("\"valueStream\":"));
        Assertions.assertTrue(output.contains("\"domain\":"));
    }
}
