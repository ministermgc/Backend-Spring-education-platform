package com.krisanov.codenest.util.impl;

import com.krisanov.codenest.common.exception.ImageNotFoundException;
import com.krisanov.codenest.util.ImageToBase64StringConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * An implementation of the ImageConverter interface for Base64 image conversion.
 *
 * <p>
 * This class provides a method for converting an image file from a local disk to a Base64 String representation.
 * </p>
 *
 * @see com.krisanov.codenest.util.ImageToBase64StringConverter
 */
@Component
public class ImageConverterImpl implements ImageToBase64StringConverter {

    /**
     * Convert the image file located at the specified path into a Base64 String.
     *
     * <p>
     * This method reads the image file from the specified path, converts it to a byte array,
     * then encodes it as a Base64 String. If an image is not found at the specified path or if an
     * IOException is encountered during reading, an ImageNotFoundException is thrown.
     * </p>
     *
     * @param imageUrl a String representing the local file path of the image to convert
     * @return a Base64 String representing the image
     * @throws ImageNotFoundException if an image is not found at the specified path or if an IOException is encountered during reading
     */
    @Override
    public String convertImage(String imageUrl) {
        try {
            Path imagePath = Paths.get(imageUrl);
            byte[] imageData = Files.readAllBytes(imagePath);

            String base64Image = Base64.getEncoder().encodeToString(imageData);
            return "data:image/png;base64," + base64Image;
        } catch (IOException e) {
            throw new ImageNotFoundException("Image not found", e);
        }
    }
}
