package com.github.fabiitch.nz.unit.java.debug;

import com.github.fabiitch.nz.gdx.debug.DebugDisplayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class DebugDisplayUtilsTest {

    //TODO a reprendre
    @Test
    @Disabled
    public void floatFormatterTest() {
        float float1 = 5.655656454654f;
        String s1 = DebugDisplayUtils.printValue(float1);
        String[] split1 = s1.split("\\.");
        Assertions.assertEquals(2, split1.length);
        Assertions.assertEquals(3, split1[1].length());

        float float2 = 59788545454.655656454654f;
        String s2 = DebugDisplayUtils.printValue(float2);
        String[] split2 = s2.split("\\.");
        Assertions.assertEquals(1, split2.length);


        double double1 = 8.999965165462d; //9
        String s3 = DebugDisplayUtils.printValue(double1);
        String[] split3 = s3.split(",");
        Assertions.assertEquals(1, split3.length);

        double double2 = 8978978978978.999965165462d;
        String s4 = DebugDisplayUtils.printValue(double2);
        String[] split4 = s4.split(",");
        Assertions.assertEquals(1, split4.length);


        double double3 = 8.45577889554;
        String s5 = DebugDisplayUtils.printValue(double3);
        String[] split5 = s5.split(",");
        Assertions.assertEquals(2, split5.length);
        Assertions.assertEquals(3, split1[1].length());
    }

}
