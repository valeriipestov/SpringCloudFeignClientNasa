package com.example.marspictmp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.marspictmp.service.PictureService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class MarsController {

    private final PictureService pictureService;

    @GetMapping(value = "/mars/pictures/largest", produces = "image/jpeg")
    @ResponseBody
    public byte[] getLargestPicture(@RequestParam Integer sol, @RequestParam(required = false) String camera) {
        return pictureService.getLargestPicture(sol, camera);
    }
}
