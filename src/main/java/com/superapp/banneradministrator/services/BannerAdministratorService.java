package com.superapp.banneradministrator.services;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.superapp.banneradministrator.BO.GeneralResponseBuilder;
import com.superapp.banneradministrator.Exceptions.BannerAdministratorException;
import org.apache.commons.codec.binary.Base64;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.superapp.banneradministrator.Entities.BodyEliminarImagenRequestDTO;
import com.superapp.banneradministrator.Entities.BodyImagenRequestDTO;
import com.superapp.banneradministrator.Entities.BucketInfo;
import com.superapp.banneradministrator.Entities.GeneralResponseDTO;
import com.superapp.banneradministrator.daos.BannerAdministratorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


import java.util.Arrays;
import java.util.List;

@Component
public class BannerAdministratorService {

    @Autowired
    BannerAdministratorDao bannerAdministratorDao;

    public ResponseEntity<GeneralResponseDTO> ObtenerBucketsS3() {
        try {
            GeneralResponseBuilder builder = new GeneralResponseBuilder();
            List<Bucket> buckets = bannerAdministratorDao.ObtenerBucketsS3();

            if(buckets.size() <= 0){
                throw new BannerAdministratorException(Arrays.asList("No se encontraron buckets disponibles"));
            }

            GeneralResponseDTO generalResponseDTO = builder.conResultado(buckets).build();

            return ResponseEntity.ok(generalResponseDTO);
        }catch (AmazonS3Exception s3Exception){

            throw new BannerAdministratorException(Arrays.asList(s3Exception.getMessage()));
        }
    }
    public ResponseEntity<GeneralResponseDTO> obtenerInfoBucket(String nombreBucket) {
        GeneralResponseBuilder builder = new GeneralResponseBuilder();
        BucketInfo bucketInfo =  bannerAdministratorDao.obtenerInfoBucket(nombreBucket);
        GeneralResponseDTO generalResponseDTO= builder.conResultado(bucketInfo).build();
        return ResponseEntity.ok(generalResponseDTO);
    }

    public ResponseEntity<GeneralResponseDTO> subirImagenS3(BodyImagenRequestDTO body){
        GeneralResponseBuilder builder = new GeneralResponseBuilder();
        String data = body.getImagenBase64().substring(body.getImagenBase64().indexOf(",")+1);
        String tipo = body.getImagenBase64().substring(body.getImagenBase64().indexOf(":")+1,body.getImagenBase64().indexOf(";"));
        String[] extension = tipo.split("/");

        byte[] bI = Base64.decodeBase64(data);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bI.length);
        metadata.setContentType(tipo);

        String ruta = body.getRutaS3().concat("/").concat(body.getNombreArchivo()).concat(".").concat(extension[1]);
        System.out.println(ruta);
        bannerAdministratorDao.subirImagenS3(body.getNombreBucket(),ruta,bI,metadata);
        GeneralResponseDTO generalResponseDTO= builder.build();;
        return ResponseEntity.ok(generalResponseDTO);
    }

    public ResponseEntity<GeneralResponseDTO> eliminarImagenS3(BodyEliminarImagenRequestDTO body){
        GeneralResponseBuilder builder = new GeneralResponseBuilder();
        String ruta = body.getRutaS3().concat("/").concat(body.getNombreImagen()).concat(".").concat(body.getExtension());
        System.out.println(ruta);
        bannerAdministratorDao.eliminarImagenS3(body.getNombreBucket(),ruta);
        GeneralResponseDTO generalResponseDTO= builder.conMensaje("Operacion exitosa").build();
        return ResponseEntity.ok(generalResponseDTO);
    }
}
