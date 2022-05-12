package hr.fer.infsus.staem.exception;

public class ArticleAlreadyBought extends RuntimeException {

    private static final String MESSAGE = "Article is already bought!";

    public ArticleAlreadyBought() {
        super(MESSAGE);
    }

}
