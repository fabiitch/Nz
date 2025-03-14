package com.github.fabiitch.nz.java.data.quadtree.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuadQueryParams {

    private boolean current;
    private boolean parentOverlap;
    private boolean children;
}
