package lk.server.cocktails.utils;

import java.util.function.Function;

public class MyStreamUtils {
    public static <T,R> Function<T,R> wrap(Function<T,R> function) {
        return item -> {
            try {
                return function.apply(item);
            } catch (Exception e) {
                return null;
            }
        };
    }
}
