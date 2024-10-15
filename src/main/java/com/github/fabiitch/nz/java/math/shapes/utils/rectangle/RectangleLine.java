package com.github.fabiitch.nz.java.math.shapes.utils.rectangle;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.github.fabiitch.nz.java.data.collections.utils.ArrayUtils;
import com.github.fabiitch.nz.java.function.Filter;
import com.github.fabiitch.nz.java.math.utils.Orientation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class RectangleLine {

    private static Filter<Part> filterSize = part -> part.getSize() > 0;

    private Orientation orientation;
    private Rectangle totalRectangle;

    private Array<Part> cuts = new Array<>();

    public RectangleLine(Orientation orientation, Rectangle totalRectangle) {
        this.orientation = orientation;
        this.totalRectangle = totalRectangle;
    }

    public float getTotalSize() {
        return RectangleUtils.getMax(totalRectangle, orientation);
    }

    public void addCut(float position, float size) {
        Part part = new Part(position, size);
        part.resize(getTotalSize());
        if (filterSize.accept(part)) {
            cuts.add(part);
            merge(cuts);
        }
    }

    public void addFill(float position, float size) {
        Part part = new Part(position, size);
        part.resize(getTotalSize());
        for (Part cut : cuts) {
            if (cut.overlapsStick(part)) {
            }
        }
    }

    private void recompute() {
        cuts.forEach(p -> p.resize(RectangleUtils.getMax(totalRectangle, orientation)));

    }

    public float max() {
        return RectangleUtils.getSize(totalRectangle, orientation);
    }

    private Array<Part> merge(Array<Part> parts) {
        ArrayUtils.filter(parts, filterSize);
        parts.sort((a, b) -> Float.compare(a.position, b.position));
        if (parts.isEmpty())
            return parts;

        Array<Part> newParts = new Array<>();

        Part current = parts.get(0);
        for (int i = 1; i < parts.size; i++) {
            Part next = parts.get(i);

            // Si les deux parties se chevauchent ou sont collées, on les fusionne
            if (current.overlapsStick(next)) {
                current.merge(next);
            } else {
                // Sinon, on ajoute la partie actuelle et on passe à la suivante
                newParts.add(current);
                current = next;
            }
        }
        newParts.add(current);
        return newParts;
    }


    @AllArgsConstructor
    @Getter
    private static class Part {
        float position;
        float size;

        public boolean overlapsStick(Part other) {
            return this.position <= other.max() && this.max() >= other.position;
        }

        public boolean overlaps(Part other) {
            return this.position < other.max() && this.max() > other.position;
        }


        public float max() {
            return position + size;
        }

        public void merge(Part other) {
            this.position = Math.min(this.position, other.position);
            this.size = Math.max(this.size, other.size);
        }

        public void resize(float totalSize) {
            if (max() > totalSize) {
                size = totalSize - position;
            }
        }
    }
}
