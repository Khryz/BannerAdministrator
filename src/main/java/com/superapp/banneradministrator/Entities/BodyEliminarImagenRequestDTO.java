package com.superapp.banneradministrator.Entities;

import javax.validation.constraints.NotBlank;

public class BodyEliminarImagenRequestDTO {
    @NotBlank(message = "El campo nombreImagen es requerido")
    private String nombreImagen;
    @NotBlank(message = "El campo nombreBucket es requerido")
    private String nombreBucket;
    @NotBlank(message = "El campo rutaS3 es requerido")
    private String rutaS3;
    @NotBlank(message = "El campo extension es requerido")
    private String extension;

    public String getNombreImagen() {return nombreImagen;}
    public void setNombreImagen(String nombreImagen) {this.nombreImagen = nombreImagen;}
    public String getNombreBucket() {return nombreBucket;}
    public void setNombreBucket(String nombreBucket) {this.nombreBucket = nombreBucket;}

    public String getRutaS3() {return rutaS3;}
    public void setRutaS3(String rutaS3) {this.rutaS3 = rutaS3;}
    public String getExtension() {return extension;}
    public void setExtension(String extension) {this.extension = extension;}
}
