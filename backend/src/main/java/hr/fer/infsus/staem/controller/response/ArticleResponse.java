package hr.fer.infsus.staem.controller.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ArticleResponse {

    private Long id;

    private String title;

    private String pictureUrl;

    private BigDecimal price;

    private String currency;

}
