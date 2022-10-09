package com.example.marspictmp.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

@FeignClient(name = "nasa", url = "${nasa.url}")
public interface NasaFeignClient {

    @GetMapping(value = "/rovers/curiosity/photos")
    JsonNode getNasaPhotos(@RequestParam String api_key, @RequestParam Integer sol,
                           @RequestParam(required = false) String camera);

}
