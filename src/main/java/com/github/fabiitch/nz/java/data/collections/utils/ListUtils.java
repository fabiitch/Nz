package com.github.fabiitch.nz.java.data.collections.utils;

import com.github.fabiitch.nz.java.utils.ObjectUtils;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class ListUtils {
    public static <T> boolean allEquals(List<T> listA,  List<T> listB ) {
        if(ObjectUtils.oneNull(listA, listB)){
            return false;
        }
        if(listA.size() != listB.size())
            return false;
      for(int i = 0; i < listA.size(); i++){
          if(!listA.get(i).equals(listB.get(i)))
            return false;
      }
        return true;
    }
    public static <T> T getFirst(List<T> list) {
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    public static <T> T getLast(List<T> list) {
        if (list.isEmpty())
            return null;
        return list.get(list.size() - 1);
    }

    public static <T> List<T> takeXLast(List<T> list, int xLast) {
        int sizeList = list.size();
        int take = Math.min(xLast, sizeList);

        List<T> result = new ArrayList<>(take);
        for (int i = 0; i < take; i++) {
            T data = list.get(sizeList - i - 1);
            result.add(data);
        }
        return result;
    }

    public static <T> List<T> toArrayList(T... values) {
        return new ArrayList<>(Arrays.asList(values));
    }

    public static boolean isEmpty(List<?> list) {
        return list != null && list.isEmpty();
    }

    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }
}
