package com.superapp.banneradministrator.Exceptions;

import com.superapp.banneradministrator.Entities.GeneralResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

public class CustomExceptionHandler {
    @ExceptionHandler(BannerAdministratorException.class)
    public ResponseEntity<GeneralResponseDTO> bannerExcepcion(BannerAdministratorException bannerException){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setDetalles(bannerException.getDetalles());
        generalResponseDTO.setCodigo("400.SuperApp-BannerAdministrator.4001");
        generalResponseDTO.setMensaje("Ocurri\u00f3 un error interno");

        return new ResponseEntity<>(generalResponseDTO, bannerException.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponseDTO> handlerException(Exception ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setMensaje("Lo sentimos, por el momento no podemos realizar su operaci\u00f3n");
        generalResponseDTO.setDetalles(Arrays.asList(ex.getMessage()));
        generalResponseDTO.setMensaje("Internal Error");

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
