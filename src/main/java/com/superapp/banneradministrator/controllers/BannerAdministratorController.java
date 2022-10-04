package com.superapp.banneradministrator.controllers;

import com.superapp.banneradministrator.Entities.BodyEliminarImagenRequestDTO;
import com.superapp.banneradministrator.Entities.BodyImagenRequestDTO;
import com.superapp.banneradministrator.Entities.GeneralResponseDTO;
import com.superapp.banneradministrator.services.BannerAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/s3/administrador")
@Validated
public class BannerAdministratorController {

    @Autowired
    BannerAdministratorService bannerAdministratorService;

    @GetMapping("/actuator/health/readinessState")
    public ResponseEntity<Health> healthCheck(){
        return ResponseEntity.ok(Health.up().build());
    }

    @GetMapping("/bucket")
    public ResponseEntity<GeneralResponseDTO> ObtenerBucketsS3(
    ){
        return bannerAdministratorService.ObtenerBucketsS3();
    }

    @GetMapping("/informacion")
    public ResponseEntity<GeneralResponseDTO> obtenerInfoBucket(
            @Valid
            @RequestParam(name = "nombreBucket")
            String nombreBucket
    ){
    return bannerAdministratorService.obtenerInfoBucket(nombreBucket);
    }

    @PostMapping("/imagen")
    public ResponseEntity<GeneralResponseDTO> subirImagenS3(
            @RequestBody
            @Valid
            BodyImagenRequestDTO bodyImagen
    ){
        return bannerAdministratorService.subirImagenS3(bodyImagen);
    }

    @DeleteMapping("/imagen")
    public ResponseEntity<GeneralResponseDTO> eliminarImagenS3(
            @Valid
            @RequestBody
            BodyEliminarImagenRequestDTO bodyImagen
        ){
        return bannerAdministratorService.eliminarImagenS3(bodyImagen);
    }
}
