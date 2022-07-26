package service;

import model.Customer;

import java.util.Collection;

public class CustomerService {
    //0.Provide a static reference

    // 1. New up static object of this class
    public static CustomerService customerService = new CustomerService();

    //2. make the constructor private so that this class cannot be instantiated
    private CustomerService() {
    }

    //3. get the instance of the object, only one available
    public static CustomerService getInstance() {
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName)
    {

    }

    public Customer getCustomer(String customerEmail)
    {
        //so it compiles
        return new Customer("Blair", "Brown", "bb@juno.com");
    }

    public Collection<Customer> getAllCustomers() {
        //so it compliles
        return null;
    }


}
