package hr.fer.infsus.staem.exception;

public class EntityNotFound extends RuntimeException {

    private static final String MESSAGE = "Entity %s could not be found using these search criteria: %s!";

    public EntityNotFound(Class<?> notFoundClass, String[] searchParameters) {
        super(String.format(MESSAGE, notFoundClass.getSimpleName(), String.join(" ", searchParameters)));
    }

}
