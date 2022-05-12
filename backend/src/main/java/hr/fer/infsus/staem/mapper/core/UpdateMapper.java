package hr.fer.infsus.staem.mapper.core;

public interface UpdateMapper<T, U> {

    void map(T source, U target);

}
