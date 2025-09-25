package com.github.fabiitch.nz.gdx.asset;

import com.badlogic.gdx.assets.AssetManager;

public class AssetManagerUtils {

    public static int incRefCount(String fileName, AssetManager assetManager) {
        return incRefCount(fileName, 1, assetManager);
    }

    public static int incRefCount(String fileName, int inc, AssetManager assetManager) {
        int referenceCount = assetManager.getReferenceCount(fileName);
        referenceCount += inc;
        assetManager.setReferenceCount(fileName, referenceCount);
        return referenceCount;
    }

    public static int decRefCount(String fileName, AssetManager assetManager) {
        return decRefCount(fileName, 1, assetManager);
    }

    public static int decRefCount(String fileName, int dec, AssetManager assetManager) {
        return incRefCount(fileName, -dec, assetManager);
    }
}
