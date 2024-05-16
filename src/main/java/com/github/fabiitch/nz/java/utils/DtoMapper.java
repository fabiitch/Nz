package com.github.fabiitch.nz.java.utils;

import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.List;

public interface DtoMapper<D, O> {

    O toObject(D dto);

    D toDTO(O object);


    default Array<O> toArrayObject(Array<D> dtos) {
        Array<O> res = new Array<>();
        for (D dto : dtos)
            res.add(toObject(dto));
        return res;
    }

    default Array<O> toArrayObject(List<D> dtos) {
        Array<O> res = new Array<>();
        for (D dto : dtos)
            res.add(toObject(dto));
        return res;
    }

    default List<O> toListObject(List<D> dtos) {
        List<O> res = new ArrayList<>();
        for (D dto : dtos)
            res.add(toObject(dto));
        return res;
    }

    default List<O> toListObject(Array<D> dtos) {
        List<O> res = new ArrayList<>();
        for (D dto : dtos)
            res.add(toObject(dto));
        return res;
    }

    default List<D> toListDTO(List<O> objects) {
        List<D> res = new ArrayList<>();
        for (O object : objects)
            res.add(toDTO(object));
        return res;
    }

    default List<D> toListDTO(Array<O> objects) {
        List<D> res = new ArrayList<>();
        for (O object : objects)
            res.add(toDTO(object));
        return res;
    }

    default Array<D> toArrayDTO(List<O> objects) {
        Array<D> res = new Array<>();
        for (O object : objects)
            res.add(toDTO(object));
        return res;
    }

    default Array<D> toArrayDTO(Array<O> objects) {
        Array<D> res = new Array<>();
        for (O object : objects)
            res.add(toDTO(object));
        return res;
    }
}
