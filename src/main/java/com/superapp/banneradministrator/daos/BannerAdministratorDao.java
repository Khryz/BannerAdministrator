package com.superapp.banneradministrator.daos;

import com.amazonaws.services.s3.model.*;
import com.superapp.banneradministrator.Entities.ArchivoBucket;
import com.superapp.banneradministrator.Entities.BucketInfo;
import com.superapp.banneradministrator.config.AmazonConfig;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.services.s3.AmazonS3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class BannerAdministratorDao {
    @Autowired
    private AmazonConfig conexionS3;

    public  List<Bucket> ObtenerBucketsS3() throws AmazonS3Exception{
        AmazonS3 s3 = conexionS3.conectarS3();
        List<Bucket> buckets = s3.listBuckets();
        return buckets;
    }

    public BucketInfo obtenerInfoBucket(String nombreBucket) throws AmazonS3Exception{
        AmazonS3 s3 = conexionS3.conectarS3();
        ListObjectsV2Result result = s3.listObjectsV2(nombreBucket);
        List<S3ObjectSummary> objects = result.getObjectSummaries();
        List<ArchivoBucket> archivos = new ArrayList<>();

        for (S3ObjectSummary os : objects) {
            System.out.println("- " + os.getKey());
            if(os.getSize()!=0){
                ArchivoBucket archivo = new ArchivoBucket();
                BeanUtils.copyProperties(os,archivo);
                String url = s3.getUrl(nombreBucket,archivo.getKey()).toString();
                archivo.setResourceUrl(url);
                archivos.add(archivo);
            }
        }

        BucketInfo bucketInfo = new BucketInfo();
        bucketInfo.setArchivos(archivos);
        return bucketInfo;
    }
    public void subirImagenS3(String nombreBucket,String nombreArchivo, byte[] imagenbase64, ObjectMetadata metadatos){
        InputStream fis = new ByteArrayInputStream(imagenbase64);
        AmazonS3 s3 = conexionS3.conectarS3();
        s3.putObject(nombreBucket,nombreArchivo, fis, metadatos);
        s3.setObjectAcl(nombreBucket, nombreArchivo, CannedAccessControlList.PublicRead);
    }

    public void eliminarImagenS3(String nombreBucket, String ruta){
        AmazonS3 s3 = conexionS3.conectarS3();
        s3.deleteObject(new DeleteObjectRequest(nombreBucket,ruta));
    }
}
