package hr.fer.infsus.staem.mapper.core.impl;

import hr.fer.infsus.staem.mapper.core.GenericUpdateMapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenericUpdateMapperImpl implements GenericUpdateMapper {

    private final ModelMapper modelMapper;

    @Override
    public <T> void map(T source, T target) {
        modelMapper.map(source, target);
    }

}
