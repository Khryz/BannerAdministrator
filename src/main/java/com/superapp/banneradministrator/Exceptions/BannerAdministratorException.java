package com.superapp.banneradministrator.Exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BannerAdministratorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private List<String> detalles;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BannerAdministratorException(List<String> detalles){
        this.detalles = detalles;
    }

    public BannerAdministratorException(List<String> detalles, HttpStatus status){
        this.detalles = detalles;
        this.status =  status;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
