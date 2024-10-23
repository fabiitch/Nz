package com.github.fabiitch.nz.java.math.utils.direction;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.fabiitch.nz.gdx.log.StrFormat;

import static com.github.fabiitch.nz.java.math.utils.direction.Direction.*;

public class DirectionUtils {


    public static Direction getNextDirOnTurn(Direction currentPos, Direction previousDir, Direction turnDir) {
        if (previousDir.getOrientation() == turnDir.getOrientation())
            throw new IllegalArgumentException("Cant turn on same orientation than previous");
        if (currentPos.getOrientation() == previousDir.getOrientation())
            throw new IllegalArgumentException("Cant have same orientation on position than previous dir");


        switch (turnDir) {
            case Top:
                if (currentPos == Top && previousDir == Right)
                    return Left;
                if (currentPos == Bot && previousDir == Right)
                    return Right;
                if (currentPos == Top && previousDir == Left)
                    return Right;
                if (currentPos == Bot && previousDir == Left)
                    return Left;
                break;
            case Bot:
                if (currentPos == Top && previousDir == Right)
                    return Right;
                if (currentPos == Bot && previousDir == Right)
                    return Left;
                if (currentPos == Top && previousDir == Left)
                    return Left;
                if (currentPos == Bot && previousDir == Left)
                    return Right;
            case Right:
                if (currentPos == Right && previousDir == Top)
                    return Bot;
                if (currentPos == Left && previousDir == Top)
                    return Top;
                if (currentPos == Right && previousDir == Bot)
                    return Top;
                if (currentPos == Left && previousDir == Bot)
                    return Bot;
            case Left:
                if (currentPos == Right && previousDir == Top)
                    return Top;
                if (currentPos == Left && previousDir == Top)
                    return Bot;
                if (currentPos == Right && previousDir == Bot)
                    return Bot;
                if (currentPos == Left && previousDir == Bot)
                    return Top;
        }
        throw new GdxRuntimeException(StrFormat.format("Missing case for currentPos={}, previousDir={}, turnDir={}", currentPos, previousDir, turnDir));
    }
}
