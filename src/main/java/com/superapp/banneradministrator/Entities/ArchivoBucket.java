package com.superapp.banneradministrator.Entities;

import java.util.Date;

public class ArchivoBucket {
    /** The key under which this object is stored */
    private String ruta;
    /** Hex encoded MD5 hash of this object's contents, as computed by Amazon S3 */
    private String tag;
    /** The size of this object, in bytes */
    private double tamanoImagen;

    /** The date, according to Amazon S3, when this object was last modified */
    private Date ultimoCambio;

    private String urlImagen;

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public double getTamanoImagen() {
        return tamanoImagen;
    }

    public void setTamanoImagen(double tamanoImagen) {
        this.tamanoImagen = tamanoImagen;
    }

    public Date getUltimoCambio() {
        return ultimoCambio;
    }

    public void setUltimoCambio(Date ultimoCambio) {
        this.ultimoCambio = ultimoCambio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
