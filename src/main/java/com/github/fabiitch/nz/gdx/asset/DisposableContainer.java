package com.github.fabiitch.nz.gdx.asset;

import com.badlogic.gdx.utils.Disposable;

import java.util.ArrayList;

public class DisposableContainer implements Disposable {

    private ArrayList<Disposable> disposables = new ArrayList<>();

    public void add(Disposable disposable) {
        disposables.add(disposable);
    }

    @Override
    public void dispose() {
        disposables.forEach(Disposable::dispose);
    }
}
