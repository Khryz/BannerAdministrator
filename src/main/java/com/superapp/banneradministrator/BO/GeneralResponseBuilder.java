package com.superapp.banneradministrator.BO;

import com.superapp.banneradministrator.Entities.GeneralResponseDTO;

import java.util.List;

public class GeneralResponseBuilder implements  GeneralResponseBO{
    private String mensaje = "Operaci\u00f3n Exitosa.";
    private Object resultado;
    private String status;
    private String info;
    private List<String> detalles;

    public GeneralResponseBuilder(){
    }

    public GeneralResponseBuilder conMensaje(String mensaje){
        this.mensaje = mensaje;
        return this;
    }

    public GeneralResponseBuilder conResultado(Object result){
        this.resultado = result;
        return this;
    }

    /*public GeneralResponseBuilder conStatus(HttpStatus status){
        this.status = status;
        return this;
    }*/

    public GeneralResponseBuilder conInfo(String info){
        this.info = info;
        return this;
    }

    public GeneralResponseBuilder conDetalles(List<String> detalles){
        this.detalles = detalles;
        return this;
    }

    @Override
    public GeneralResponseDTO build() {
        GeneralResponseDTO generalResponseDTO = new GeneralResponseDTO();

        generalResponseDTO.setResultado(this.resultado);
        generalResponseDTO.setMensaje(this.mensaje);
        generalResponseDTO.setDetalles(this.detalles);
        generalResponseDTO.setInfo(this.info);

        return generalResponseDTO;
    }
}
