package com.superapp.banneradministrator.Entities;

import javax.validation.constraints.NotBlank;

public class BodyEliminarImagenRequestDTO {
    @NotBlank(message = "El campo nombreBucket es requerido")
    private String nombreBucket;
    @NotBlank(message = "El campo rutaS3 es requerido")
    private String rutaAbsoluta;

    public String getNombreBucket() {return nombreBucket;}
    public void setNombreBucket(String nombreBucket) {this.nombreBucket = nombreBucket;}

    public String getRutaAbsoluta() {
        return rutaAbsoluta;
    }

    public void setRutaAbsoluta(String rutaAbsoluta) {
        this.rutaAbsoluta = rutaAbsoluta;
    }
}
