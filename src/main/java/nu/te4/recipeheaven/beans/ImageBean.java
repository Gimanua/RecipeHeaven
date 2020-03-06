/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.te4.recipeheaven.beans;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import javax.ejb.Stateless;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adrian Klasson
 */
@Stateless
public class ImageBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageBean.class);
    
    public void deleteImage(int id) {
        try {
            File image = new File(PropertyBean.getProperty(PropertyBean.ImageProperty.PATH) + id + ".jpg");
            image.delete();
        } catch(Exception ex){
            LOGGER.debug("Failed to delete image: {}", ex.getMessage());
        }

    }

    public void saveImageToDisk(String imageData, int id) throws IOException {
        byte[] decodedImage = Base64.getDecoder().decode(imageData.getBytes());
        Path filePath = Paths.get(PropertyBean.getProperty(PropertyBean.ImageProperty.PATH) + id + ".jpg");
        Files.write(filePath, decodedImage);
    }

    public File getImage(int recipeId) {
        String path = PropertyBean.getProperty(PropertyBean.ImageProperty.PATH) + recipeId + ".jpg";
        return new File(path);
    }
}
