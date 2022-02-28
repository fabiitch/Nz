package com.fabiitch.nz.unit.algo;

import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.algo.InitOrdererAlgo;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InitOrdererAlgoTest {

    @Test
    public void serviceInitTest() {
        InitOrdererAlgoMock s1 = new InitOrdererAlgoMock();
        InitOrdererAlgoMock s2 = new InitOrdererAlgoMock(s1);
        InitOrdererAlgoMock s3 = new InitOrdererAlgoMock();
        InitOrdererAlgoMock s4 = new InitOrdererAlgoMock(s1, s3);
        InitOrdererAlgoMock s5 = new InitOrdererAlgoMock(s2, s3);
        InitOrdererAlgoMock s6 = new InitOrdererAlgoMock(s5);
        InitOrdererAlgoMock s7 = new InitOrdererAlgoMock();

        Array<InitOrdererAlgo> array = new Array<>();
        array.add(s1, s2, s3, s4);
        array.add(s5, s6, s7);

        assertDoesNotThrow(() -> InitOrdererAlgo.initAll(array));

        array.forEach(s -> {
            InitOrdererAlgoMock mock = (InitOrdererAlgoMock) s;
            assertTrue(mock.initCount == 1);
        });
    }

    @Test
    public void serviceInitFailTest() {
        InitOrdererAlgoMock s1 = new InitOrdererAlgoMock();
        InitOrdererAlgoMock s2 = new InitOrdererAlgoMock();
        InitOrdererAlgoMock s3 = new InitOrdererAlgoMock();

        s1.setWaiting(s2, s3);
        s2.setWaiting(s1);

        Array<InitOrdererAlgo> array = new Array<>();
        array.add(s1, s2, s3);

        assertThrows(Exception.class, () -> InitOrdererAlgo.initAll(array));

    }

    private class InitOrdererAlgoMock implements InitOrdererAlgo {

        private InitOrdererAlgo[] servicesToWait;
        public int initCount = 0;

        public InitOrdererAlgoMock(InitOrdererAlgo... servicesToWait) {
            this.servicesToWait = servicesToWait;
        }

        public void setWaiting(InitOrdererAlgo... servicesToWait) {
            this.servicesToWait = servicesToWait;
        }

        @Override
        public List<InitOrdererAlgo> waitingList() {
            return Arrays.asList(servicesToWait);
        }

        @Override
        public void init() {
            initCount++;
        }

        @Override
        public void afterAllInit() {

        }
    }
}
