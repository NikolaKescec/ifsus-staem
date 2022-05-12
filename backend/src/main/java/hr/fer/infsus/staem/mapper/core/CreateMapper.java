package hr.fer.infsus.staem.mapper.core;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

public interface CreateMapper<T, U> {

    U map(T source);

    default List<U> mapToList(List<T> list) {
        return list.stream().map(this::map).collect(Collectors.toList());
    }

    default Page<U> mapToPage(Page<T> page, Class<U> targetClass) {
        final List<U> mappedList = this.mapToList(page.getContent());

        return new PageImpl<>(mappedList, page.getPageable(), page.getTotalElements());
    }

}
