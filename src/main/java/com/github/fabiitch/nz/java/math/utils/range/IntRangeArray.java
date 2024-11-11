package com.github.fabiitch.nz.java.math.utils.range;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import lombok.Getter;

public class IntRangeArray {
    @Getter
    private int start, end;
    private final Array<IntRange> subRange = new Array<>();
    private final Pool<IntRange> pool;

    public IntRangeArray(int start, int end) {
        this(start, end, null);
    }

    public IntRangeArray(int start, int end, Pool<IntRange> pool) {
        this.start = start;
        this.end = end;
        subRange.add(new IntRange(start, end));

        if (pool != null) {
            this.pool = pool;
        } else {
            this.pool = new Pool<IntRange>() {
                @Override
                protected IntRange newObject() {
                    return new IntRange();
                }
            };
        }
    }

    public Array<IntRange> getSubRange() {
        Array<IntRange> res = new Array<>();
        for (IntRange range : subRange) {
            res.add(getRange(range.getStart(), range.getEnd()));
        }
        return res;
    }

    public Array<IntRange> getCuts() {
        Array<IntRange> res = new Array<>();

        if (subRange.isEmpty()) {
            res.add(getRange(start, end));
            return res;
        }

        IntRange startSub = ArrayUtils.getFirst(subRange);
        if (startSub.getStart() > this.start) {
            res.add(getRange(start, startSub.getStart() - 1));
        }
        for (int i = 0; i < subRange.size - 1; i++) {
            IntRange cut = getRange(subRange.get(i).getEnd() + 1, subRange.get(i + 1).getStart() - 1);
            res.add(cut);
        }
        IntRange endSub = ArrayUtils.getLast(subRange);

        if (endSub.getEnd() < this.end) {
            res.add(getRange(endSub.getEnd() + 1, end));
        }
        return res;
    }


    public void setStart(int start, boolean add) {
        if (this.start == start)
            return;

        int oldStart = this.start;
        this.start = start;

        if (oldStart < this.start) {
            removeValues(oldStart, this.start);
        } else {
            if (add)
                addValues(oldStart, start);
        }
    }

    public void setEnd(int end, boolean add) {
        if (this.end == end)
            return;

        int oldEnd = this.end;
        this.end = end;

        if (this.end < oldEnd) {
            endLess();
        } else {
            if (add)
                addValues(oldEnd, this.end);
        }
    }


    public void addValues(int start, int end) {
        IntRange newRange = getRange(start, end);

        for (int i = 0; i < subRange.size; i++) {
            IntRange range = subRange.get(i);
            if (range.intersectStick(newRange)) {
                newRange.merge(range);
                subRange.removeIndex(i);
                pool.free(range);
                i--;
            }
        }
        subRange.add(newRange);
        subRange.sort();
    }

    private void endLess() {
        for (int i = 0; i < subRange.size; i++) {
            IntRange range = subRange.get(i);
            if (range.contains(this.end)) {
                range.setEnd(this.end);
            }
        }
    }

    private void startMore() {
        for (int i = 0; i < subRange.size; i++) {
            IntRange range = subRange.get(i);
            if (range.contains(this.start)) {
                range.setStart(this.start);
            }
        }
    }

    public boolean hasStart(int start) {
        return start >= this.start;
    }

    public boolean hasEnd(int end) {
        return end <= this.end;
    }

    public boolean has(int start, int end) {
        return hasStart(start) && hasEnd(end);
    }

    public IntRangeArray removeValues(int start, int end) {
        IntRange rangeRemove = getRange(start, end);
        for (int i = 0; i < subRange.size; i++) {
            IntRange range = subRange.get(i);
            if (rangeRemove.contains(range)) {
                subRange.removeIndex(i);
                i--;
            }
            if (!rangeRemove.intersect(range))
                continue;

            if (range.intersectMiddle(rangeRemove)) {
                IntRange rightNewRange = getRange(rangeRemove.getEnd() + 1, range.getEnd());
                range.setEnd(rangeRemove.getStart() - 1);
                subRange.insert(i + 1, rightNewRange);
                i++;
            } else if (range.intersectBegin(rangeRemove)) {
                range.setStart(rangeRemove.getEnd() + 1);
            } else if (range.intersectEnd(rangeRemove)) {
                range.setEnd(rangeRemove.getStart() - 1);
            }
        }
        return this;
    }

    private void checkRange(IntRange range) {
        if (range.getStart() < this.start)
            range.setStart(this.start);
        if (range.getEnd() > this.end)
            range.setEnd(this.end);
    }

    private IntRange getRange(int start, int end) {
        if (start < this.start)
            start = this.start;
        if (end > this.end)
            end = this.end;

        IntRange range = pool.obtain();
        range.set(start, end);
        return range;
    }
}
