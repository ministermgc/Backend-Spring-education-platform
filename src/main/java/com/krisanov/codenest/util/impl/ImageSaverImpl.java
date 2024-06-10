package com.krisanov.codenest.util.impl;

import com.krisanov.codenest.common.exception.ImageNotSavedException;
import com.krisanov.codenest.util.ImageSaverReturnUrl;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

/**
 * An implementation of the ImageSaverReturnUrl interface.
 *
 * <p>
 * Images are saved into a directory with a unique filename generated
 * using UUID, and the method returns the full path to the saved image file
 * as a string upon successful completion.
 * </p>
 *
 * @see com.krisanov.codenest.util.ImageSaverReturnUrl
 */
@Component
public class ImageSaverImpl implements ImageSaverReturnUrl {

    private static final String OUTPUT_PATH = "images/";

    /**
     * Checks for the existence of the output directory and creates it if it doesn't exist.
     */
    private void checkAndCreateOutputDirectory() {
        Path path = Paths.get(OUTPUT_PATH);
        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.err.println("Error creating directory: " + OUTPUT_PATH);
            }
        }
    }

    /**
     * Saves an image represented as a byte array into a directory with a unique filename.
     *
     * @param data a byte array representing the image to be saved.
     * @return a String representing the full path of the saved image file.
     * @throws ImageNotSavedException if an error occurs during file writing.
     */
    @Override
    public String saveImage(byte[] data) {
        checkAndCreateOutputDirectory();
        Path path = Path.of("images/" + UUID.randomUUID() + ".png");
        try {
            return Files.write(path, data,
                    StandardOpenOption.CREATE, StandardOpenOption.WRITE).toString();
        } catch (IOException e) {
            throw new ImageNotSavedException("[EXCEPTION] Image not saved", e);
        }
    }
}
