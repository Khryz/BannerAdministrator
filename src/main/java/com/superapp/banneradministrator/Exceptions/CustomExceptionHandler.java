package com.superapp.banneradministrator.Exceptions;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
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
    private static final String MENSAJE_SDK_CLIENTE_AWS = "Amazon S3 no pudo procesar la solicitud";
    private static final String CODIGO_INTERNAL_SERVER_ERROR = "Lo sentimos, por el momento no podemos realizar su operaci\u00f3n";
    private static final String DETALLES_PARAMETROS_NO_VALIDOS = "No se inform\u00f3 el par\u00e1metro: ";
    private static final String MENSAJE_INTERNAL_SERVER_ERROR = "Error interno";
    private static final String MENSAJE_BANNER_ADMINISTRATOR = "Ocurri\u00f3 un error inesperado";

    @ExceptionHandler(BannerAdministratorException.class)
    public ResponseEntity<GeneralResponseDTO> bannerExcepcion(BannerAdministratorException bannerException){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setDetalles(bannerException.getDetalles());
        generalResponseDTO.setCodigo(CODIGO_BAD_REQUEST);
        generalResponseDTO.setMensaje(MENSAJE_BANNER_ADMINISTRATOR);

        return new ResponseEntity<>(generalResponseDTO, bannerException.getStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GeneralResponseDTO> parametrosExcepcion(MissingServletRequestParameterException ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setCodigo(CODIGO_BAD_REQUEST+"2");
        generalResponseDTO.setMensaje(BAD_REQUEST_MSJ);
        generalResponseDTO.setDetalles(Arrays.asList(DETALLES_PARAMETROS_NO_VALIDOS+ex.getParameterName()));

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponseDTO> bodyExcepcion(MethodArgumentNotValidException ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().stream().forEach(err -> errors.add(err.getDefaultMessage()) );

        generalResponseDTO.setCodigo(CODIGO_BAD_REQUEST+"3");
        generalResponseDTO.setMensaje(BAD_REQUEST_MSJ);
        generalResponseDTO.setDetalles(errors);

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<GeneralResponseDTO> amazonServiceException(AmazonServiceException  ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setCodigo(CODIGO_BAD_REQUEST+"4");
        generalResponseDTO.setMensaje(BAD_REQUEST_MSJ);
        generalResponseDTO.setDetalles(Arrays.asList(ex.getMessage()));

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SdkClientException.class)
    public ResponseEntity<GeneralResponseDTO> sdkClientException(SdkClientException ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setCodigo(CODIGO_INTERNAL_SERVER_ERROR+"1");
        generalResponseDTO.setMensaje(MENSAJE_SDK_CLIENTE_AWS);
        generalResponseDTO.setDetalles(Arrays.asList(ex.getMessage()));

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponseDTO> excepcionGeneral(Exception ex){
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setMensaje(CODIGO_INTERNAL_SERVER_ERROR+"0");
        generalResponseDTO.setDetalles(Arrays.asList(ex.getMessage()));
        generalResponseDTO.setMensaje(MENSAJE_INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(generalResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
