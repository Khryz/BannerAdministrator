package com.superapp.banneradministrator.controllers;

import com.superapp.banneradministrator.Entities.BodyEliminarImagenRequestDTO;
import com.superapp.banneradministrator.Entities.BodyImagenRequestDTO;
import com.superapp.banneradministrator.Entities.BucketInfo;
import com.superapp.banneradministrator.Entities.GeneralResponseDTO;
import com.superapp.banneradministrator.services.BannerAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import static com.superapp.banneradministrator.Utils.BannerAdministratorConstants.SICU_HEADER;

@RestController
@RequestMapping("/v1/s3/administrador")
public class BannerAdministratorController {

    @Autowired
    BannerAdministratorService bannerAdministratorService;

    @GetMapping("/bucket")
    public ResponseEntity<GeneralResponseDTO> ObtenerBucketsS3(
    ){
        return bannerAdministratorService.ObtenerBucketsS3();
    }

    //cambiar por objeto de respuesta
    @GetMapping("/informacion")
    public ResponseEntity<GeneralResponseDTO> obtenerInfoBucket(
            @Valid @NotNull(message = "No se recibi\u00f3 nombreBucket") @RequestParam(name = "nombreBucket")
                    String nombreBucket
    ){
    return bannerAdministratorService.obtenerInfoBucket(nombreBucket);
    }

    //Cambiar objeto de respuesta
    @PostMapping("/imagen")
    public ResponseEntity<GeneralResponseDTO> subirImagenS3(
            @Valid @NotNull(message = "No se recibi\u00f3 request body") @RequestBody
                    BodyImagenRequestDTO bodyImagen
    ){
        return bannerAdministratorService.subirImagenS3(bodyImagen);
    }

    @DeleteMapping("/imagen")
    public ResponseEntity<GeneralResponseDTO> eliminarImagenS3(
            @Valid @NotNull(message = "No se recibi\u00f3 request body") @RequestBody
                    BodyEliminarImagenRequestDTO bodyImagen
        ){
        return bannerAdministratorService.eliminarImagenS3(bodyImagen);
    }

}
