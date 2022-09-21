package com.superapp.banneradministrator.Entities;

public class BodyEliminarImagenRequestDTO {
    private String nombreImagen;
    private String nombreBucket;
    private String rutaS3;
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
