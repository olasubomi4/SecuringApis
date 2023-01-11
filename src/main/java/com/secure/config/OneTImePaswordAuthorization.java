package com.secure.config;


import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.security.Principal;

@RolesAllowed("")
@Provider
@Priority(Priorities.AUTHORIZATION)
public class OneTImePaswordAuthorization implements ContainerRequestFilter {
    @Context
    private ResourceInfo info;
    @Context
    private SecurityContext sec;
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

       if(!userHasPermission(requestContext,info.getResourceMethod().getAnnotation(RolesAllowed.class)))
       {
           throw new NotAuthorizedException("You dont have permission to this class");
       }
    }
    public boolean userHasPermission(ContainerRequestContext requestContext, RolesAllowed rolesAllowed)
    {
        String a=sec.getUserPrincipal().getName();
        String b=a;
        String authorization = requestContext.getHeaderString(
                HttpHeaders.AUTHORIZATION);
        String[] split = authorization.split(" ");
        final String user = split[0];
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

        if (rolesAllowed==null) {
            return true;
        }
        else if(doesRoleNameMatchUserName(rolesAllowed,securityContext))
        {
            return true;
        } else {
            return  false;
        }


    }
    public boolean doesRoleNameMatchUserName(RolesAllowed rolesAllowed,SecurityContext securityContext)
    {
        for(String role: rolesAllowed.value())
        {
            String su= role;
            String s=securityContext.getUserPrincipal().getName();
            if(role.equals(securityContext.getUserPrincipal().getName()))
            {
                return true;
            }
        }
        return false;
    }
}
