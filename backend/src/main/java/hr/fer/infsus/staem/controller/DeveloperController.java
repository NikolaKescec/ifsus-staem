package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.response.GenreResponse;
import hr.fer.infsus.staem.mapper.GenericCreateMapper;
import hr.fer.infsus.staem.service.DeveloperQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/developer")
@AllArgsConstructor
public class DeveloperController {

    private DeveloperQueryService developerQueryService;

    private GenericCreateMapper genericCreateMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GenreResponse> findAll() {
        return genericCreateMapper.mapToList(developerQueryService.findAll(), GenreResponse.class);
    }

}
