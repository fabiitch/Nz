package com.fabiitch.nz.test.unit;

import com.badlogic.gdx.utils.Array;
import com.fabiitch.nz.depends.InitWaitOther;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO a move
public class InitWaitOtherTest {

    @Test
    public void serviceInitTest() {
        InitWaitOtherMock s1 = new InitWaitOtherMock();
        InitWaitOtherMock s2 = new InitWaitOtherMock(s1);
        InitWaitOtherMock s3 = new InitWaitOtherMock();
        InitWaitOtherMock s4 = new InitWaitOtherMock(s1, s3);
        InitWaitOtherMock s5 = new InitWaitOtherMock(s2, s3);
        InitWaitOtherMock s6 = new InitWaitOtherMock(s5);
        InitWaitOtherMock s7 = new InitWaitOtherMock();

        Array<InitWaitOther> array = new Array<>();
        array.add(s1, s2, s3, s4);
        array.add(s5, s6, s7);

        assertDoesNotThrow(() -> InitWaitOther.initAll(array));

        array.forEach(s -> {
            InitWaitOtherMock mock = (InitWaitOtherMock) s;
            assertTrue(mock.initCount == 1);
        });
    }

    @Test
    public void serviceInitFailTest() {
        InitWaitOtherMock s1 = new InitWaitOtherMock();
        InitWaitOtherMock s2 = new InitWaitOtherMock();
        InitWaitOtherMock s3 = new InitWaitOtherMock();

        s1.setWaiting(s2, s3);
        s2.setWaiting(s1);

        Array<InitWaitOther> array = new Array<>();
        array.add(s1, s2, s3);

        assertThrows(Exception.class, () -> InitWaitOther.initAll(array));

    }

    private class InitWaitOtherMock implements InitWaitOther {

        private InitWaitOther[] servicesToWait;
        public int initCount = 0;

        public InitWaitOtherMock(InitWaitOther... servicesToWait) {
            this.servicesToWait = servicesToWait;
        }

        public void setWaiting(InitWaitOther... servicesToWait) {
            this.servicesToWait = servicesToWait;
        }

        @Override
        public List<InitWaitOther> waitingList() {
            return Arrays.asList(servicesToWait);
        }

        @Override
        public void create() {
            initCount++;
        }

        @Override
        public void afterAllInit() {

        }
    }
}
