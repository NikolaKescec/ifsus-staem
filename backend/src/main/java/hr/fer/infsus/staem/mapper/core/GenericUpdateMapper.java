package hr.fer.infsus.staem.mapper.core;

public interface GenericUpdateMapper {

    <T> void map(T source, T target);

}
