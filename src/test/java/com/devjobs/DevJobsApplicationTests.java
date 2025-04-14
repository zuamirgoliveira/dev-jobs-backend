package com.devjobs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevJobsApplicationTests {

    @Test
    void contextLoads() {
        // The application context should load without exceptions.
    }

    @Test
    void mainRunsWithoutErrors() {
        DevJobsApplication.main(new String[]{});
    }
}
