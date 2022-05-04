package hr.fer.infsus.staem.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenericCreateMapperImpl implements GenericCreateMapper {

    private final ModelMapper modelMapper;

    @Override
    public <T, U> U map(T source, Class<U> targetClass) {
        return this.modelMapper.map(source, targetClass);
    }

}
