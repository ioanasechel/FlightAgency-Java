package Domain.Validators;

public interface Validator<T> {

    /**
     * validates an entity
     * @param entity T
     * @throws ValidationException Exception
     */
    void validate(T entity) throws ValidationException;
}