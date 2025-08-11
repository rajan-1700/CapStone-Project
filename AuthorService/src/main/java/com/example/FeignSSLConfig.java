package com.example;


import feign.Client;
import feign.httpclient.ApacheHttpClient; // ✅ correct package
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLContext;

public class FeignSSLConfig {

    @Bean
    public Client feignClient() throws Exception {
        SSLContext sslContext = SSLContextBuilder.create()
                .loadTrustMaterial((chain, authType) -> true) // Trust all certs
                .build();

        return new ApacheHttpClient(HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build());
    }
}