package hr.fer.infsus.staem.mapper;

public interface UpdateMapper<T, U> {

    void map(T source, U target);

}
