package com.github.fabiitch.nz.java.math.path.rectangle.corridor;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.github.fabiitch.nz.java.math.shapes.utils.RectangleUtils;
import com.github.fabiitch.nz.java.math.utils.direction.Direction;
import com.github.fabiitch.nz.java.math.utils.direction.Orientation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorridorPart {

    private Direction direction;
    private Vector2 middleStart, middleEnd;
    private Rectangle rectWallA, rectWallB;
    private Rectangle rectWalk;

    private Rectangle blockerRectA, blockerRectB;

    private Rectangle totalRect;

    public Orientation getOrientation() {
        return direction.getOrientation();
    }

    public Orientation getOtherOrientation() {
        return direction.getOtherOrientation();
    }

    public float getWallSize(Direction posWall) {
        return RectangleUtils.getSize(getWall(posWall), direction.getOtherOrientation());
    }

    public float getWallLength(Direction posWall) {
        return RectangleUtils.getSize(getWall(posWall), direction.getOrientation());
    }

    public Rectangle getWall(Direction posWall) {
        if (posWall.getOrientation() == direction.getOrientation())
            throw new IllegalArgumentException("wall and corridor part cant have same orientation");

        if (posWall == direction.getOtherOrientation().getDirectionA())
            return rectWallA;
        else
            return rectWallB;
    }

    public float getLength() {
        return RectangleUtils.getSize(rectWalk, direction.getOrientation());
    }

    public float getWalkSize() {
        return RectangleUtils.getSize(rectWalk, direction.getOtherOrientation());
    }

    public float getWallSizeA() {
        return RectangleUtils.getSize(rectWallA, direction.getOtherOrientation());
    }

    public float getWallSizeB() {
        return RectangleUtils.getSize(rectWallB, direction.getOtherOrientation());
    }

    public float getLengthWallA() {
        return RectangleUtils.getSize(rectWallA, direction.getOrientation());
    }

    public float getLengthWallB() {
        return RectangleUtils.getSize(rectWallB, direction.getOrientation());
    }


    public float getTotalSize() {
        return getWalkSize() + getWallSizeA() + getWallSizeB();
    }

    public void setBlocker(Direction wallDir, Rectangle blockerRect) {
        if (wallDir.getOrientation() == direction.getOrientation())
            throw new IllegalArgumentException("blocker and corridor part cant have same orientation");

        Rectangle wall = getWall(wallDir);
        if (wall == rectWallA)
            setBlockerRectA(blockerRect);
        setBlockerRectB(blockerRect);
    }


    public float getDstFromCenter(Direction wallDirection){
       return getWallSize(wallDirection) + getWalkSize()/2;
    }

    public Rectangle getTotalRect() {
        if (totalRect == null)
            computeTotalRect();
        return totalRect;
    }

    public void computeTotalRect() {
        if (rectWalk != null) {
            totalRect = new Rectangle(rectWalk);
            if (rectWallA != null)
                totalRect.merge(rectWallA);
            if (rectWallB != null)
                totalRect.merge(rectWallB);
        }
    }

    public boolean hasBlockers() {
        return blockerRectA != null || blockerRectB != null;
    }
}
