package com.example.mulino.service;

import com.example.mulino.model.MenuItem;
import com.example.mulino.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MenuItemService {


    @Autowired
    private MenuItemRepository menuItemRepository;

    private final Path root;
    private final String picturesDirectory = "src/main/resources/static/pictures";

    public MenuItemService() {
        try {
            root = Paths.get(getClass().getClassLoader().getResource("static/pictures").toURI());
            Files.createDirectories(root); // Ensure the directory exists
        } catch (Exception e) {
            throw new RuntimeException("Could not create upload folder!", e);
        }
    }


    public MenuItem findById(Long id) {
        return menuItemRepository.findById(id).orElse(null);
    }
    public List<MenuItem> findAll() {
        return menuItemRepository.findAll();
    }

    public Resource getPicture(Long itemId) {
        MenuItem item = findById(itemId);
        if (item != null && item.getPicture() != null) {
            String pictureFileName = item.getPicture();
            try {
                Path picturePath = Paths.get(picturesDirectory).resolve(pictureFileName).normalize();
                Resource pictureResource = new UrlResource(picturePath.toUri());
                if (pictureResource.exists() && pictureResource.isReadable()) {
                    return pictureResource;
                } else {
                    return null;
                }

            } catch (MalformedURLException ex) {
                return null;
            }
        } else {
            return null;
        }
    }
}
