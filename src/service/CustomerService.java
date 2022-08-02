package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //data structure to house all the customers
    Map<String, Customer> customersMap = new HashMap<String, Customer>();


    public void addCustomer(String email, String firstName, String lastName)
    {
        try {
            Customer customer = new Customer(email, firstName, lastName);
            customersMap.put(customer.getEmail(), customer);
            // dont add if customer already exists
            if (customersMap.containsKey(customer.getEmail())) {
                System.out.println("Account for " + customer.getFirstName() + " " + customer.getLastName() + " was successfully added.");
            }
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Customer getCustomer(String customerEmail)
    {
        //will this error out if customer map is null?
        boolean customerFound = customersMap.containsKey(customerEmail);
        if (customerFound) {
            return customersMap.get(customerEmail);
        }
        else return null;
    }

    public List<Customer> getAllCustomers() {
        return customersMap.values().stream().toList();
    }


}
