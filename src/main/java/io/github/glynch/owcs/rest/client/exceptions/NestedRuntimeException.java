package io.github.glynch.owcs.rest.client.exceptions;

public abstract class NestedRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Construct a {@code NestedRuntimeException} with the specified detail message.
     * 
     * @param msg the detail message
     */
    public NestedRuntimeException(String message) {
        super(message);
    }

    /**
     * Construct a {@code NestedRuntimeException} with the specified detail message
     * and nested exception.
     * 
     * @param msg   the detail message
     * @param cause the nested exception
     */
    public NestedRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NestedRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * Retrieve the innermost cause of this exception, if any.
     * 
     * @return the innermost exception, or {@code null} if none
     */
    public Throwable getRootCause() {
        Throwable cause = getCause();
        if (cause == null) {
            return this;
        }
        Throwable nextCause = cause;
        while ((nextCause = nextCause.getCause()) != null) {
            cause = nextCause;
        }
        return cause;
    }

    /**
     * Retrieve the most specific cause of this exception, that is,
     * either the innermost cause (root cause) or this exception itself.
     * <p>
     * Differs from {@link #getRootCause()} in that it falls back
     * to the present exception if there is no root cause.
     * 
     * @return the most specific cause (never {@code null})
     */
    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return (rootCause != null ? rootCause : this);
    }

}
