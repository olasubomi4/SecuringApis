package com.secure.config;

import com.secure.controller.CustomerResource;
import com.secure.controller.CustomerResourceTest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("v1")
public class Appconfig extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public Appconfig(){
        singletons.add(new CustomerResource());
        HashMap<String, String> userSecretMap = new HashMap<String, String>();
        userSecretMap.put("bburke", "geheim");
        userSecretMap.put("admin", "admin");
        singletons.add(new OneTimePasswordAuthenticator(userSecretMap));
        singletons.add(new PerDayAuthorizer());
        singletons.add(new OneTimePasswordGenerator());
        singletons.add(new OneTImePaswordAuthorization());
    }
//    public Set<Class<?>> getClasses() {
//        Set<Class<?>> resources = new HashSet<>();
//        addRestResourceClasses(resources);
//        return resources;
//    }
//    private void addRestResourceClasses(Set<Class<?>> resources) {
//        resources.add(CustomerResource.class);
//        resources.add(CustomerResourceTest.class);
       // resources.add(OneTimePasswordAuthenticator.class);
//        resources.add(OTPAuthenticated.class);
//        resources.add(OTP.class);
//        resources.add(OneTimePasswordAuthenticator.class);
@Override
public Set<Object> getSingletons() {

        return singletons;
}
}
