package com.secure.model;

import lombok.Data;

import javax.ws.rs.HeaderParam;

@Data
public class filterBean {
    @HeaderParam("to")
    String tok;
}
