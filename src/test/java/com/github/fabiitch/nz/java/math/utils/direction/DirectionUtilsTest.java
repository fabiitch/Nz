package com.github.fabiitch.nz.java.math.utils.direction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.github.fabiitch.nz.java.math.utils.direction.Direction.*;
import static com.github.fabiitch.nz.java.math.utils.direction.DirectionUtils.getNextDirOnTurn;

public class DirectionUtilsTest {

    @Test
    public void getNextDirOnTurnTest() {
        //virage Top
        Assertions.assertEquals(Left, getNextDirOnTurn(Top, Right, Top));
        Assertions.assertEquals(Right, getNextDirOnTurn(Bot, Right, Top));
        Assertions.assertEquals(Right, getNextDirOnTurn(Top, Left, Top));
        Assertions.assertEquals(Left, getNextDirOnTurn(Bot, Left, Top));

        //virage Bot
        Assertions.assertEquals(Right, getNextDirOnTurn(Top, Right, Bot));
        Assertions.assertEquals(Left, getNextDirOnTurn(Bot, Right, Bot));
        Assertions.assertEquals(Left, getNextDirOnTurn(Top, Left, Bot));
        Assertions.assertEquals(Right, getNextDirOnTurn(Bot, Left, Bot));

        //virage Right
        Assertions.assertEquals(Bot, getNextDirOnTurn(Right, Top, Right));
        Assertions.assertEquals(Top, getNextDirOnTurn(Left, Top, Right));
        Assertions.assertEquals(Top, getNextDirOnTurn(Right, Bot, Right));
        Assertions.assertEquals(Bot, getNextDirOnTurn(Left, Bot, Right));

        //virage Left
        Assertions.assertEquals(Top, getNextDirOnTurn(Right, Top, Left));
        Assertions.assertEquals(Bot, getNextDirOnTurn(Left, Top, Left));
        Assertions.assertEquals(Bot, getNextDirOnTurn(Right, Bot, Left));
        Assertions.assertEquals(Top, getNextDirOnTurn(Left, Bot, Left));

        Assertions.assertThrows(IllegalArgumentException.class, () -> getNextDirOnTurn(Right, Right, Top));
    }
}
