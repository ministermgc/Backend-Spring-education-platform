package com.krisanov.codenest.util;

/**
 * An interface for image saving operations.
 *
 * <p>
 * This interface declares a method for saving an image, which is provided in the form of a byte array.
 * </p>
 *
 * @param <T> the type of result expected after saving the image.
 */
public interface ImageSaver<T> {

    /**
     * Saves an image represented as a byte array.
     *
     * @param bytes a byte array representing the image to be saved.
     * @return an instance of type T that represents the result of the saving operation.
     */
    T saveImage(byte[] bytes);
}