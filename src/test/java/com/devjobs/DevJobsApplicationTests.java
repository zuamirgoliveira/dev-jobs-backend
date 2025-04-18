package com.devjobs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class DevJobsApplicationTests {

    @Test
    void contextLoads() {
        // The application context should load without exceptions.
    }

    @Test
    void mainRunsWithoutErrors() {
        assertDoesNotThrow(() -> DevJobsApplication.main(new String[]{}));
    }
}
