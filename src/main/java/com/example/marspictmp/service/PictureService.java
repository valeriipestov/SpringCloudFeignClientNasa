package com.example.marspictmp.service;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.marspictmp.controller.NasaFeignClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PictureService {

    @Value("${nasa.api_key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    private final NasaFeignClient nasaController;

    @Cacheable("largest")
    public byte[] getLargestPicture(Integer sol, String camera) {
        var photosNodeResponse = nasaController.getNasaPhotos(apiKey, sol, camera);
        return photosNodeResponse.findValues("img_src").parallelStream()
          .map(url -> {
              var picUrl = restTemplate.headForHeaders(url.asText()).getLocation();
              return restTemplate.getForObject(picUrl, byte[].class);
          }).max(Comparator.comparing(pic -> pic.length))
          .orElseThrow(() -> new NoSuchElementException("Picture not found"));
    }
}
