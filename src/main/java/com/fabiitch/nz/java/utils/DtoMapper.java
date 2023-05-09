package com.fabiitch.nz.java.utils;

import java.util.ArrayList;
import java.util.List;

public interface DtoMapper<D, O> {


    O toObject(D dto);

    D toDTO(O object);


    default List<O> toObjects(List<D> dtos) {
        List<O> res = new ArrayList<>();
        for (D dto : dtos)
            res.add(toObject(dto));
        return res;
    }

    default List<D> toDtos(List<O> objects) {
        List<D> res = new ArrayList<>();
        for (O object : objects)
            res.add(toDTO(object));
        return res;
    }
}
