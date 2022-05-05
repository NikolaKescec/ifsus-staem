package hr.fer.infsus.staem.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Entity %s could not be found using these search criteria: %s!";

    public EntityNotFoundException(Class<?> notFoundClass, String[] searchParameters) {
        super(String.format(MESSAGE, notFoundClass.getSimpleName(), String.join(" ", searchParameters)));
    }

}
