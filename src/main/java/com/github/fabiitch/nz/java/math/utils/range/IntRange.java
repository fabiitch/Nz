package com.github.fabiitch.nz.java.math.utils.range;

import com.badlogic.gdx.utils.Pool;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class IntRange implements Comparable<IntRange>, Pool.Poolable {

    private int start, end;

    public IntRange(int start, int end) {
        set(start, end);
    }

    public boolean contains(int value) {
        return value >= start && value <= end;
    }

    public boolean contains(IntRange other) {
        return this.start <= other.start && this.end >= other.end;
    }

    public boolean intersect(IntRange other) {
        return this.start <= other.getEnd() && this.end >= other.getStart();
    }

    public boolean stick(IntRange other) {
        return this.end == other.start - 1 || this.start == other.end + 1;
    }

    public boolean intersectStick(IntRange other) {
        return intersect(other) || stick(other);
    }

    public boolean intersectBegin(IntRange other) {
        return other.start <= this.start && other.end <= this.end;
    }

    public boolean intersectEnd(IntRange other) {
        return this.start <= other.start && this.end <= other.end;
    }

    public boolean intersectMiddle(IntRange other) {
        return other.start > this.start && other.end < this.end;
    }


    public void merge(IntRange other) {
        this.start = Math.min(this.start, other.start);
        this.end = Math.max(this.end, other.end);
    }

    public void set(int start, int end) {
        this.start = Math.min(start, end);
        this.end = Math.max(start, end);
    }

    @Override
    public void reset() {
        start = end = 0;
    }

    @Override
    public int compareTo(IntRange o) {
        return Integer.compare(start, o.start);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        IntRange intRange = (IntRange) object;
        return start == intRange.start && end == intRange.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    public boolean equals(IntRange other) {
        return this.start == other.start && this.end == other.end;
    }

    @Override
    public String toString() {
        return "IntRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
