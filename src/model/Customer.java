package model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    private final String emailRegex = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegex);
    //private final Matcher matcher = pattern.matcher("jeff@example.com"); //not used

    public Customer(String firstName, String lastName, String email)
    {
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, invalid email address");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //how do you know which need getters and setters?
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " Last Name: " + lastName + " Email: " + email;
    }
}
