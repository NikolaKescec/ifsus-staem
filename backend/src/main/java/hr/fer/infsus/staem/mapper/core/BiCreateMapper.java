package hr.fer.infsus.staem.mapper.core;

public interface BiCreateMapper<T, U, V> {

    V map(T firstSource, U secondSource);

}
