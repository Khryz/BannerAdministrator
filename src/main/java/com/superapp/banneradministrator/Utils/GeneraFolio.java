package com.superapp.banneradministrator.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneraFolio {
    public static String generaFolio(){
        LocalDateTime fechaHoy = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        String formattedLocalDate = fechaHoy.format(formatter);

        formattedLocalDate = formattedLocalDate
                .replace(" ","")
                .replaceAll(":","")
                .replaceAll("-","");

        return formattedLocalDate;
    }
}
