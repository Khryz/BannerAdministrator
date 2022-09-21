package com.superapp.banneradministrator.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.stereotype.Component;

@Component
public class AmazonConfig {
    public AmazonS3 conectarS3(){
        AmazonS3 s3Client = null;
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAYRMUE3EFI46742HL", "8BhtNVGNJXyRP0utqhcd702J0IEVHa3e3hy6M/zL");
        s3Client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.US_WEST_1)
                .build();

        //Cambiar al rol de EC2
        /*AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new InstanceProfileCredentialsProvider(false))
                .build();*/

        return s3Client;
    }
}
