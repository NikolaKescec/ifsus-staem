package hr.fer.infsus.staem.controller;

import hr.fer.infsus.staem.controller.request.create.CreatePublisherRequest;
import hr.fer.infsus.staem.controller.request.update.UpdatePublisherRequest;
import hr.fer.infsus.staem.controller.response.PublisherResponse;
import hr.fer.infsus.staem.exception.IdMismatchException;
import hr.fer.infsus.staem.mapper.core.GenericCreateMapper;
import hr.fer.infsus.staem.service.PublisherCommandService;
import hr.fer.infsus.staem.service.PublisherQueryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/publisher")
@AllArgsConstructor
public class PublisherController {

    private PublisherCommandService publisherCommandService;

    private PublisherQueryService publisherQueryService;

    private GenericCreateMapper genericCreateMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PublisherResponse> findAll() {
        return genericCreateMapper.mapToList(publisherQueryService.findAll(), PublisherResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('create:publisher')")
    public PublisherResponse create(@RequestBody @Valid CreatePublisherRequest createPublisherRequest) {
        return genericCreateMapper.map(publisherCommandService.create(createPublisherRequest.getName()),
            PublisherResponse.class);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('update:publisher')")
    public PublisherResponse update(@PathVariable("id") Long id,
        @RequestBody @Valid UpdatePublisherRequest updatePublisherRequest) {
        if (!id.equals(updatePublisherRequest.getId())) {
            throw new IdMismatchException(id, updatePublisherRequest.getId());
        }

        return genericCreateMapper.map(
            publisherCommandService.update(updatePublisherRequest.getId(), updatePublisherRequest.getName()),
            PublisherResponse.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('delete:publisher')")
    public void delete(@PathVariable("id") Long id) {
        publisherCommandService.delete(id);
    }

}
