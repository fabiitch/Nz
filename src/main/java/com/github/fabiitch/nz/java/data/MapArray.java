package com.github.fabiitch.nz.java.data;

import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.Map;

public class MapArray<K, V> {

    private Map<K, Array<V>> map = new HashMap<>();

    public void put(K key, V value) {
        Array<V> array = map.get(key);
        if (array == null) {
            array = new Array<>();
            map.put(key, array);
        }
        array.add(value);
    }

    public boolean clear(K key) {
        Array<V> array = map.get(key);
        if (array != null) {
            array.clear();
            return true;
        }
        return false;
    }

    public boolean remove(K key) {
        return map.remove(key) != null;
    }

    public boolean remove(K key, V value) {
        Array<V> array = map.get(key);
        if (array != null)
            return array.removeValue(value, true);
        return false;
    }

    public Array<V> get(K key) {
        return map.get(map);
    }

    public Map<K, Array<V>> getMap() {
        return map;
    }

}
