package hr.fer.infsus.staem.repository.query;

import hr.fer.infsus.staem.validator.validpricerange.ValidPriceRange;
import hr.fer.infsus.staem.validator.validpricerange.ValidPriceRangeFields;
import lombok.Data;

@ValidPriceRange
@Data
public class PriceRange implements ValidPriceRangeFields {

    private Double minPrice;

    private Double maxPrice;

}
