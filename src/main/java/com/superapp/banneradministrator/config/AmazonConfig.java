package com.superapp.banneradministrator.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClientBuilder;
import com.amazonaws.services.securitytoken.model.AssumeRoleRequest;
import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.Credentials;
import org.springframework.stereotype.Component;

@Component
public class AmazonConfig {
    public AmazonS3 conectarS3(){
        //arn:aws:iam::206757627403:role/ecsTaskExecutionRole
        String roleARN = "arn:aws:iam::772440241300:role/Assume_Rol";
        String rolaSesionName = "Session_1";

        AWSSecurityTokenService awsSecurityTokenService = AWSSecurityTokenServiceClientBuilder.standard().build();
        AssumeRoleRequest roleRequest = new AssumeRoleRequest().withRoleArn(roleARN)
                .withRoleSessionName(rolaSesionName).withDurationSeconds(3600);
        AssumeRoleResult assumeRoleResult = awsSecurityTokenService.assumeRole(roleRequest);
        Credentials temporalCredentials  = assumeRoleResult.getCredentials();

        BasicSessionCredentials basicSessionCredentials = new BasicSessionCredentials(
                temporalCredentials.getAccessKeyId(),
                temporalCredentials.getSecretAccessKey(),
                temporalCredentials.getSessionToken()
        );

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicSessionCredentials))
                .build();

        return s3Client;
    }
}