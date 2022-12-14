package com.superapp.banneradministrator.daos;

import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.Md5Utils;
import com.superapp.banneradministrator.Entities.ArchivoBucket;
import com.superapp.banneradministrator.Entities.BucketInfo;
import com.superapp.banneradministrator.Exceptions.BannerAdministratorException;
import com.superapp.banneradministrator.config.AmazonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amazonaws.services.s3.AmazonS3;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

                archivo.setRuta(os.getKey());
                archivo.setTag(os.getETag());
                archivo.setTamanoImagen(Double.parseDouble(new DecimalFormat("##.#").format(((double) os.getSize())/1024)));
                archivo.setUltimoCambio(os.getLastModified());

                String url = s3.getUrl(nombreBucket,archivo.getRuta()).toString();
                archivo.setUrlImagen(url);
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
        String md5 = Md5Utils.md5AsBase64(imagenbase64);
        PutObjectResult result = s3.putObject(nombreBucket,nombreArchivo, fis, metadatos);

        if(!md5.equals(result.getContentMd5())){
            throw new BannerAdministratorException(Arrays.asList("No se pudo subir la imagen"));
        }
    }

    public void eliminarImagenS3(String nombreBucket, String ruta){
        AmazonS3 s3 = conexionS3.conectarS3();
        s3.deleteObject(new DeleteObjectRequest(nombreBucket,ruta));
    }
}
