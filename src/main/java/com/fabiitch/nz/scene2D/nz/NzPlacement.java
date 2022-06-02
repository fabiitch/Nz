package com.fabiitch.nz.scene2D.nz;

import com.fabiitch.nz.scene2D.data.PosSize;

public class NzPlacement {

    public PosSize posSize;

    public boolean sizePercent;
    public boolean posPercent;

    public boolean witdhAsSizePercentTotal;
    public boolean heightAsSizePercentTotal;

    public NzPlacement(PosSize posSize) {
        this.posSize = posSize;
    }

    public NzPlacement sizePercent(boolean sizePercent) {
        this.sizePercent = sizePercent;
        return this;
    }

    public NzPlacement allPercent() {
        return this.allPercent(true);
    }

    public NzPlacement allPercent(boolean allPercent) {
        this.sizePercent = allPercent;
        this.posPercent = allPercent;
        return this;
    }

    public NzPlacement posPercent(boolean posPercent) {
        this.posPercent = posPercent;
        return this;
    }

    public NzPlacement witdhAsSizePercentTotal() {
        this.witdhAsSizePercentTotal = true;
        this.heightAsSizePercentTotal = false;
        return this;
    }

    public NzPlacement heightAsSizePercentTotal() {
        this.witdhAsSizePercentTotal = false;
        this.heightAsSizePercentTotal = true;
        return this;
    }
}
