package com.secure.config;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class OneTimePasswordGenerator implements ClientRequestFilter {
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        String user="admin";
        String secret="admin";
        String otp = OTP.generateToken(secret);
        requestContext.getHeaders().putSingle
                (HttpHeaders.AUTHORIZATION, user + " " + otp);
    }
}
