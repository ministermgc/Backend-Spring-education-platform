package com.krisanov.codenest.common.exception;

/**
 * A custom runtime exception that is thrown when an image is not found.
 *
 * <p>
 * This class extends RuntimeException and takes in a message string and an additional throwable
 * parameter in its constructor to provide more details about the error.
 * </p>
 *
 * @author Maxim Krisanov
 * @see java.lang.RuntimeException
 */
public class ImageNotFoundException extends RuntimeException {

    /**
     * Constructs a new ImageNotFoundException with the specified detail message.
     *
     * @param message the detail message saved for later retrieval by the Throwable.getMessage() method.
     */
    public ImageNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ImageNotFoundException with the specified detail message and cause.
     *
     * <p>Note that the detail message associated with cause is not automatically incorporated
     * in this exception's detail message.</p>
     *
     * @param message   the detail message (which is saved for later retrieval by the Throwable.getMessage() method).
     * @param throwable the cause (which is saved for later retrieval by the Throwable.getCause() method).
     *                  (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ImageNotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
