package com.krisanov.codenest.util.mapper;

import com.krisanov.codenest.common.dto.ImageRequest;
import com.krisanov.codenest.util.ImageSaverReturnUrl;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * The ImageRequestMapper class mapping an ImageRequest object into a string URL.
 *
 * <p>
 * This class is an abstract Mapper class, using the MapStruct library to map
 * properties from an ImageRequest object to a String URL. It uses an instance of
 * the ImageSaverReturnUrl to save the image and then returns its URL.
 * </p>
 */
@Mapper(componentModel = "spring", uses = ImageSaverReturnUrl.class)
public abstract class ImageRequestMapper {

    /**
     * ImageSaverReturnUrl instance used for saving images.
     */
    private ImageSaverReturnUrl imageSaver;

    /**
     * Sets the ImageSaverReturnUrl used to save the ImageRequest
     *
     * @param imageSaver the ImageSaverReturnUrl to set
     */
    @Autowired
    public void setImageSaver(ImageSaverReturnUrl imageSaver) {
        this.imageSaver = imageSaver;
    }

    /**
     * Converts the ImageRequest to an image URL.
     *
     * @param imageRequest the ImageRequest to be converted
     * @return a String representing the URL of the saved image
     */
    @Named(value = "toImageUrl")
    public String toImageUrl(ImageRequest imageRequest) {
        return imageSaver.saveImage(imageRequest.data());
    }
}