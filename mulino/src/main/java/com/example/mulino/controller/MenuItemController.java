package com.example.mulino.controller;

import com.example.mulino.dto.MenuItemDto;
import com.example.mulino.model.MenuItem;
import com.example.mulino.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/menuItems", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping("")
    public ResponseEntity<List<MenuItemDto>> getAll() {
        List<MenuItem> menuItems = menuItemService.findAll();
        List<MenuItemDto> dtos = new ArrayList<MenuItemDto>();
        for(MenuItem menuItem : menuItems){
            MenuItemDto menuItemDto = new MenuItemDto(menuItem);
            dtos.add(menuItemDto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemDto> getById(@PathVariable Long id) {
        MenuItem menuItem = menuItemService.findById(id);

        MenuItemDto menuItemDto = new MenuItemDto(menuItem);

        return new ResponseEntity<>(menuItemDto, HttpStatus.OK);
    }

    @GetMapping("/picture/{itemId}")
    public ResponseEntity<Resource> getMainPicture(@PathVariable Long itemId) {
        Resource picture = menuItemService.getPicture(itemId);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(picture);
    }

}