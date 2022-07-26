package model;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    private String email;
    private String firstName;
    private String lastName;

    private final String emailRegex = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    //override hashcode
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //override equals
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Customer(String email, String firstName, String lastName)
    {
        if(!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Error, invalid email address");
        }
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

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
        return "First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email;
    }
}
