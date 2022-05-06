package hr.fer.infsus.staem.exception;

public class IdMismatchException extends RuntimeException {

    private static final String MESSAGE = "Id mismatch: %d != %d";

    public IdMismatchException(Long id1, Long id2) {
        super(String.format(MESSAGE, id1, id2));
    }

}
