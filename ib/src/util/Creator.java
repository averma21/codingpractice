package util;

import java.util.ArrayList;
import java.util.List;

public class Creator {

    public static <T> List<T> getList(T... elements) {
        List<T> list = new ArrayList<T>(elements.length);
        for (T ele : elements) {
            list.add(ele);
        }
        return list;
    }

}
