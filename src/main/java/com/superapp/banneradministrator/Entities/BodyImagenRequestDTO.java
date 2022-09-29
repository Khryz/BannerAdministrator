package com.superapp.banneradministrator.Entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BodyImagenRequestDTO {
    @NotBlank(message = "El campo imagenBase64 es requerido")
    private String imagenBase64;
    @NotBlank(message = "El campo rutaS3 es requerido")
    private String rutaS3;
    @NotBlank(message = "El campo nombreBucket es requerido")
    private String nombreBucket;
    @NotBlank(message = "El campo nombreArchivo es requerido")
    private String nombreArchivo;
    @NotBlank(message = "El campo extension es requerido")
    private String extension;

    public String getImagenBase64() {return imagenBase64;}
    public void setImagenBase64(String imagenBase64) {this.imagenBase64 = imagenBase64;}
    public String getRutaS3() {return rutaS3;}
    public void setRutaS3(String rutaS3) {this.rutaS3 = rutaS3;}
    public String getNombreBucket() {return nombreBucket;}
    public void setNombreBucket(String nombreBucket) {this.nombreBucket = nombreBucket;}
    public String getNombreArchivo() {return nombreArchivo;}
    public void setNombreArchivo(String nombreArchivo) {this.nombreArchivo = nombreArchivo;}
    public String getExtension() {return extension;}
    public void setExtension(String extension) {this.extension = extension;}
}
