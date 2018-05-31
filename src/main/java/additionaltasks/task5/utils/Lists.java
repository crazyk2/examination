package additionaltasks.task5.utils;

import java.util.ArrayList;

public class Lists {

    public static <T> ArrayList<T> newArrayList(Iterable<? extends T> elements) {
        if (elements == null) {
            return null;
        }

        ArrayList<T> list = newArrayList();
        for (T e : elements) {
            list.add(e);
        }
        return list;
    }

    public static <T> ArrayList<T> newArrayList() {
        return new ArrayList<>();
    }
}
