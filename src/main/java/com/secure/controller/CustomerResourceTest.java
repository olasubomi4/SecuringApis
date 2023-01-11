package com.secure.controller;

import com.google.gson.Gson;
import com.secure.config.OneTimePasswordGenerator;
import com.secure.model.Customer;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class CustomerResourceTest {
    Client client= ClientBuilder.newClient();
    @Test
    public void testCustomerResource() throws Exception {
//        System.out.println("*** Create a new Customer ***");
//        Customer newCustomer = new Customer();
//        newCustomer.setFirstName("Bill");
//        newCustomer.setLastName("Burke");
//        newCustomer.setStreet("256 Clarendon Street");
//        newCustomer.setCity("Boston");
//        newCustomer.setState("MA");
//        newCustomer.setZip("02115");
//        newCustomer.setCountry("USA");
//        Response response = client.target(
//                        "http://localhost:8080/Securing/v1/customers")
//                .request().post(Entity.xml(newCustomer));
//        if (response.getStatus() != 201) throw new RuntimeException ("Failed to create");
//        String location = response.getLocation().toString();
//        System.out.println("Location: " + location);
//        response.close();
        System.out.println("*** GET Created Customer **");
        Customer customer = null;
        WebTarget target = client.target("http://localhost:8080/Securing/v1/customers/1");
        try
        {
            target.register(new OneTimePasswordGenerator());
            Response customer1 = target.request().get();

         String cu= customer1.readEntity(String.class);
            System.out.println(cu);
          Customer rr= new Gson().fromJson(cu,Customer.class);
//rr.setFirstName("changed");
//
//target.register(new OneTimePasswordGenerator());
//Response response= target.request().put(Entity.json(rr));
//String res= response.readEntity(String.class);
//            System.out.println(res);
          //  Assert.fail(); // should have thrown an exception
        }
        catch (NotAuthorizedException e) {
        }
//
//target.register(new OneTimePasswordGenerator("bburke", "geheim"));

//        customer = target.request().get(Customer.class);
//        System.out.println(customer);
//
//        customer.setFirstName("William");
//        Response response = target.request().put(Entity.xml(customer));
//        if (response.getStatus() != 204)
//            throw new RuntimeException("Failed to update");

        // Show the update

//        System.out.println("**** After Update ***");
//        customer = target.request().get(Customer.class);
//        System.out.println(customer);
//// only allowed to update once per day
//        customer.setFirstName("Bill");
//        response = target.request().put(Entity.xml(customer));
//        Assert.assertEquals(Response.Status.FORBIDDEN, response.getStatusInfo());





    }
}
