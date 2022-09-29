package com.superapp.banneradministrator.Exceptions;

import com.superapp.banneradministrator.Entities.GeneralResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandler {
    private static final String CODIGO_BAD_REQUEST = "400.BannerAdministrator";
    private static final String BAD_REQUEST_MSJ = "Par\u00e1metros no v\u00e1lidos, por favor valide su informaci\u00f3n";

    @ExceptionHandler(BannerAdministratorException.class)
    public ResponseEntity<GeneralResponseDTO> bannerExcepcion(BannerAdministratorException bannerException){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setDetalles(bannerException.getDetalles());
        generalResponseDTO.setCodigo(CODIGO_BAD_REQUEST);
        generalResponseDTO.setMensaje("Ocurri\u00f3 un error inesperado");

        return new ResponseEntity<>(generalResponseDTO, bannerException.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GeneralResponseDTO> parametrosExcepcion(MissingServletRequestParameterException ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setCodigo(CODIGO_BAD_REQUEST+"2");
        generalResponseDTO.setMensaje(BAD_REQUEST_MSJ);
        generalResponseDTO.setDetalles(Arrays.asList("No se inform\u00f3 el par\u00e1metro: "+ex.getParameterName()));

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponseDTO> bodyExcepcion(MethodArgumentNotValidException ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().stream().forEach(err -> errors.add(err.getDefaultMessage()) );

        generalResponseDTO.setCodigo(CODIGO_BAD_REQUEST+"3");
        generalResponseDTO.setMensaje(BAD_REQUEST_MSJ);
        generalResponseDTO.setDetalles(errors/*Arrays.asList("No se inform\u00f3 el par\u00e1metro: "+ex.getLocalizedMessage())*/);

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponseDTO> excepcionGeneral(Exception ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setMensaje("Lo sentimos, por el momento no podemos realizar su operaci\u00f3n");
        generalResponseDTO.setDetalles(Arrays.asList(ex.getMessage()));
        generalResponseDTO.setMensaje("Error interno");

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
