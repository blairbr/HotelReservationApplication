package service;

import model.Customer;
import ui.MainMenu;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {

    public static CustomerService customerService = new CustomerService();
    private CustomerService() {
    }
    public static CustomerService getInstance() {
        return customerService;
    }
    Map<String, Customer> customersMap = new HashMap<String, Customer>();

    public void addCustomer(String email, String firstName, String lastName)
    {
        try {
            Customer customer = new Customer(email, firstName, lastName);
            if (customersMap.containsKey(customer.getEmail())) {
                System.out.println("This email address is already associated to a user in the system. Printing main menu.");
                MainMenu.printMainMenu();
            }
            customersMap.put(customer.getEmail(), customer);
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
