package hr.fer.infsus.staem.repository.query;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PageInfo {

    @Min(value = 0, message = "Page number must be greater than or equal to 0")
    private Integer page = 0;

    @Min(value = 0, message = "Page number must be greater than or equal to 0")
    @Max(value = 100, message = "Page number must be less than or equal to 100")
    private Integer size = 10;

}
