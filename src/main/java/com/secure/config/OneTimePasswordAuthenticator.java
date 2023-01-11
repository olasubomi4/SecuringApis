package com.secure.config;

import com.secure.model.filterBean;

import javax.annotation.Priority;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@OTPAuthenticated
@Provider
@Priority(Priorities.AUTHENTICATION)
public class OneTimePasswordAuthenticator implements ContainerRequestFilter {
    protected Map<String, String> userSecretMap;
    public OneTimePasswordAuthenticator(Map<String, String> userSecretMap)
    {
        this.userSecretMap = userSecretMap;
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authorization = requestContext.getHeaderString(
                HttpHeaders.AUTHORIZATION);
        String to = requestContext.getHeaderString("to");
        String f=to;
        if (authorization == null) throw new NotAuthorizedException("OTP");
        String[] split = authorization.split(" ");
        final String user = split[0];
        String otp = split[1];
        String secret = userSecretMap.get(user);
        if (secret == null) throw new NotAuthorizedException("OTP");
        String regen = OTP.generateToken(secret);
        if (!regen.equals(otp)) throw new NotAuthorizedException("OTP");

        final SecurityContext securityContext = requestContext.getSecurityContext();
        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return user; }
                }; }
            @Override
            public boolean isUserInRole(String role) {
                return false; }
            @Override
            public boolean isSecure() {
                return securityContext.isSecure(); }
            @Override
            public String getAuthenticationScheme() {
                return "OTP"; }
        });




    }
}
