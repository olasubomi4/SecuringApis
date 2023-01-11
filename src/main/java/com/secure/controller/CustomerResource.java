package com.secure.controller;

import com.secure.config.AllowedPerDay;
import com.secure.config.OTPAuthenticated;
import com.secure.customerDB;
import com.secure.model.Customer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/customers")
public class CustomerResource {
    com.secure.customerDB customerDB= new customerDB();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @OTPAuthenticated
    public Customer getCustomer(@PathParam("id") int id) {

//        if(securityContext.getUserPrincipal().getName()!="admin")
//        {
//            throw new NotAuthorizedException("n");
//        }

        customerDB.setCustomers(new Customer( 1,"suboi","odekunle","ss","lagos","ff","efe","333"));
        customerDB.setCustomers(new Customer( 0,"suboi","odekunle","ss","lagos","ff","efe","333"));
        return customerDB.get(id);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @OTPAuthenticated
    @AllowedPerDay(1)
    public Response updateCustomer(@PathParam("id") int id, Customer update) {
        customerDB.setCustomers(new Customer( 1,"suboi","odekunle","ss","lagos","ff","efe","333"));
        customerDB.setCustomers(new Customer( 0,"suboi","odekunle","ss","lagos","ff","efe","333"));
      return   Response.status(Response.Status.OK).entity(customerDB.updateCustomer(id,update)).build();
    }



}
