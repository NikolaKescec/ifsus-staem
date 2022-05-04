package hr.fer.infsus.staem.mapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public interface GenericCreateMapper {

    <T, U> U map(T source, Class<U> targetClass);

    default <T, U> List<U> mapToList(List<T> list, Class<U> targetClass) {
        return list.stream().map(item -> this.map(item, targetClass)).collect(Collectors.toList());
    }

    default <T, U> Page<U> mapToPage(Page<T> page, Class<U> targetClass) {
        final List<U> mappedList = this.mapToList(page.getContent(), targetClass);

        return new PageImpl<>(mappedList, page.getPageable(), page.getTotalElements());
    }

}
