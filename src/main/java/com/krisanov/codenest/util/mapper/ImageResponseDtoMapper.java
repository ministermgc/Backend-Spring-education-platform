package com.krisanov.codenest.util.mapper;

import com.krisanov.codenest.common.exception.ImageNotFoundException;
import com.krisanov.codenest.util.ImageToBase64StringConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A MapStruct mapper for handling the conversion of image URLs to Base64 String representations.
 *
 * <p>
 * This mapper uses an ImageConverter for the conversion process. It provides an interface to map
 * a given image URL to its corresponding Base64 representation.
 * </p>
 *
 * @see com.krisanov.codenest.util.ImageConverter
 */
@Mapper(componentModel = "spring", uses = ImageToBase64StringConverter.class)
public abstract class ImageResponseDtoMapper {

    /**
     * ImageToBase64StringConverter instance used for converting images.
     */
    private ImageToBase64StringConverter imageConverter;

    /**
     * Sets the ImageToBase64StringConverter implementation to be used in the image conversion.
     *
     * @param imageConverter the ImageConverter to set
     */
    @Autowired
    public void setImageConverter(ImageToBase64StringConverter imageConverter) {
        this.imageConverter = imageConverter;
    }

    /**
     * Converts an image URL into Base64 representation.
     *
     * <p>
     * This method uses the configured ImageToBase64StringConverter implementation to perform the conversion.
     * In case the image conversion fails due to image not found, it returns an empty string.
     * </p>
     *
     * @param imageUrl the image URL to convert
     * @return the Base64 encoded string of the image, or empty string if image is not found.
     */
    @Named("toImageResponse")
    public String toImageResponse(String imageUrl) {
        try {
            return imageConverter.convertImage(imageUrl);
        } catch (ImageNotFoundException ex) {
            return "";
        }
    }
}
