package com.secure;

import com.secure.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class customerDB {
//    Customer d= new Customer(1,"suboi","odekunle","ss","lagos","ff","efe","333"
//            );
    private List<Customer> customerslist = new ArrayList<>();

    public void setCustomers(Customer customers)
    {
        customerslist.add(customers);
    }
    public Customer get(int id)
    {
        return customerslist.get(id);
    }

    public Customer updateCustomer(int id,Customer customer)
    {
        if(get(id)==null)
        {
           throw new RuntimeException("Customer doesnt exist");
        }
        else {
            Customer customerToUpdate = get(id);
            customerToUpdate=customer;
            return customerToUpdate;
        }
    }


}
