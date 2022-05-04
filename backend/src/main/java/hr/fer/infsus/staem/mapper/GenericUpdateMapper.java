package hr.fer.infsus.staem.mapper;

public interface GenericUpdateMapper {

    <T> void map(T source, T target);

}
