package com.github.fabiitch.nz.java.math.vectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class IntVector {

    public int x, y;

    public IntVector setX(int x) {
        this.x = x;
        return this;
    }

    public IntVector setY(int y) {
        this.y = y;
        return this;
    }

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

    @Override
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            IntVector other = (IntVector) obj;
            return this.x == other.x && this.y == other.y;
        }
    }

    public boolean equals(IntVector other) {
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(x);
        result = 31 * result + Integer.hashCode(y);
        return result;
    }
}
