package lk.server.cocktails.utils;

public interface RowMapperNewType<E, R, T> {
    E join(R r, T t);
}