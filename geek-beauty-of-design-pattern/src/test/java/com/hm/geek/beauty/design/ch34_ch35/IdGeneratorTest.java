package com.hm.geek.beauty.design.ch34_ch35;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

@Slf4j
class IdGeneratorTest {
    @InjectMocks
    IdGenerator idGenerator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerate() {
        String result = IdGenerator.generate();
        log.info("idGenerator#generate is {}", result);
        Assertions.assertNotNull(result);
    }
}