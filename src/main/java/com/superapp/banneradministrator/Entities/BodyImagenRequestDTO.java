package com.superapp.banneradministrator.Entities;

public class BodyImagenRequestDTO {
    private String imagenBase64;
    private String rutaS3;
    private String nombreBucket;
    private String nombreArchivo;
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
