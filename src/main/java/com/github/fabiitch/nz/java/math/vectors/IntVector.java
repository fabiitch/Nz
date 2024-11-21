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

}
