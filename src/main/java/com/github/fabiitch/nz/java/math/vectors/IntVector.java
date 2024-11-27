package com.github.fabiitch.nz.java.math.vectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntVector {

    private int x, y;

    public IntVector set(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    public IntVector add(IntVector vector) {
        this.x += vector.x;
        this.y += vector.y;
        return this;
    }

    public IntVector sub(IntVector vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        return this;
    }

    public IntVector cpy() {
        return new IntVector(this.x, this.y);
    }

    public IntVector addX(int x) {
        this.x += x;
        return this;
    }

    public IntVector subX(int x) {
        this.x -= x;
        return this;
    }

    public IntVector addY(int y) {
        this.y += y;
        return this;
    }

    public IntVector subY(int y) {
        this.y -= y;
        return this;
    }
}
