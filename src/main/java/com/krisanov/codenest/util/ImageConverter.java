package com.krisanov.codenest.util;

/**
 * An interface for image conversion operations.
 *
 * <p>
 * This interface provides a method for converting an image located at a given URL
 * to another format or representation.
 * </p>
 *
 * @param <T> the type of object that the converted image will be represented as.
 */
public interface ImageConverter<T> {

    /**
     * Converts the image located at the provided URL.
     *
     * @param imageUrl a String representing the URL of the image to be converted.
     * @return an instance of type T that represents the converted image.
     */
    T convertImage(String imageUrl);
}
