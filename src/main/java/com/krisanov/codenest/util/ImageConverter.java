package com.krisanov.codenest.util;

/**
 * An interface for image conversion operations.
 *
 * <p>
 * This interface provides a method for converting an image located at a given URL
 * to another format or representation.
 * </p>
 *
 * @author Maxim Krisanov
 */
public interface ImageConverter {

    /**
     * Convert the image located at the provided URL.
     *
     * @param imageUrl a String representing the URL of the image to convert
     * @return a String representing the converted image
     */
    String convertImage(String imageUrl);
}
