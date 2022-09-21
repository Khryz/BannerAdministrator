package com.superapp.banneradministrator.Entities;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.superapp.banneradministrator.Utils.GeneraFolio;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponseDTO {
    private String folio;
    private String mensaje;
    private Object resultado;
    private String codigo;
    private String info;
    private List<String> detalles;

    public GeneralResponseDTO() {
        this.folio = GeneraFolio.generaFolio();
        this.mensaje = "Operaci\u00f3n Exitosa.";
    }

    public String getFolio() {return folio;}
    public String getMensaje() {return mensaje;}
    public void setMensaje(String mensaje) {this.mensaje = mensaje;}
    public Object getResultado() {return resultado;}
    public void setResultado(Object resultado) {this.resultado = resultado;}
    public String getInfo() {return info;}
    public void setInfo(String info) {this.info = info;}
    public List<String> getDetalles() {return detalles;}
    public void setDetalles(List<String> detalles) {this.detalles = detalles;}
    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
}
