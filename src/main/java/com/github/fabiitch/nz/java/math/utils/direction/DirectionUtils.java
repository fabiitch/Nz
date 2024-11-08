package com.github.fabiitch.nz.java.math.utils.direction;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.fabiitch.nz.gdx.log.StrFormat;

import static com.github.fabiitch.nz.java.math.utils.direction.Direction.*;

public class DirectionUtils {


    /**
     * @param currentPos      actual wall position from path
     * @param actualDirection actual dir
     * @param turnDir         turn dir
     * @return
     */
    public static Direction getNextDirOnTurn(Direction currentPos, Direction actualDirection, Direction turnDir) {
        if (actualDirection.getOrientation() == turnDir.getOrientation())
            throw new IllegalArgumentException("Cant turn on same orientation than actual direction");
        if (currentPos.getOrientation() == actualDirection.getOrientation())
            throw new IllegalArgumentException("Cant have same orientation on position than actual direction ");


        switch (turnDir) {
            case Top:
                if (currentPos == Top && actualDirection == Right)
                    return Left;
                if (currentPos == Bot && actualDirection == Right)
                    return Right;
                if (currentPos == Top && actualDirection == Left)
                    return Right;
                if (currentPos == Bot && actualDirection == Left)
                    return Left;
                break;
            case Bot:
                if (currentPos == Top && actualDirection == Right)
                    return Right;
                if (currentPos == Bot && actualDirection == Right)
                    return Left;
                if (currentPos == Top && actualDirection == Left)
                    return Left;
                if (currentPos == Bot && actualDirection == Left)
                    return Right;
            case Right:
                if (currentPos == Right && actualDirection == Top)
                    return Bot;
                if (currentPos == Left && actualDirection == Top)
                    return Top;
                if (currentPos == Right && actualDirection == Bot)
                    return Top;
                if (currentPos == Left && actualDirection == Bot)
                    return Bot;
            case Left:
                if (currentPos == Right && actualDirection == Top)
                    return Top;
                if (currentPos == Left && actualDirection == Top)
                    return Bot;
                if (currentPos == Right && actualDirection == Bot)
                    return Bot;
                if (currentPos == Left && actualDirection == Bot)
                    return Top;
        }
        throw new GdxRuntimeException(StrFormat.format("Missing case for currentPos={}, actualDirection={}, turnDir={}", currentPos, actualDirection, turnDir));
    }
}
