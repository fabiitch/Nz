package com.github.fabiitch.nz.java.data;

import com.badlogic.gdx.utils.Pool;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pair<T, K> implements Pool.Poolable {
    private T key;
    private K value;

    public static <T, K> Pair<T, K> of(T key, K value) {
        return new Pair<>(key, value);
    }

    @Override
    public void reset() {
        key = null;
        value = null;
    }
}
