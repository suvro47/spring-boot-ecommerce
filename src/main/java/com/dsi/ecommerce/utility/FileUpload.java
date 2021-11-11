package com.dsi.ecommerce.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.dsi.ecommerce.utility.constants.ImageType;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

    public static String saveImage(ImageType imageType, String name, MultipartFile file) {

        String staticPath = "src/main/resources/static/";

        Path uploadPath = null;

        if (imageType == ImageType.SHOP_POSTER) {
            uploadPath = Paths.get(staticPath + "images/shops/");
        } else if (imageType == ImageType.USER_PROFILE)
            uploadPath = Paths.get(staticPath + "images/users/");

        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileExtension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String newFileName = System.currentTimeMillis() + "_" + name + fileExtension;

            Path filePath = uploadPath.resolve(newFileName);

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "~/" + uploadPath.toString().substring(staticPath.length()) + "/" + newFileName;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }
}
