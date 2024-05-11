package onlineshop;

import onlineshop.enums.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Customer {
    /**
     * Generates a new customer number for each customer
     */
    private static Integer customerCounter = 1;
    /**
     * Converts the date string into a {@link Date}
     */
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");

    protected int customerNo;
    protected String firstname;
    protected String surname;
    protected Gender gender;
    protected LocalDate birthDate;
    protected Cart cart;

    public Customer() {
        this.customerNo = customerCounter++;
    }

    public Customer(String firstname, String surname, Gender gender, String birthDate, Cart cart) {
        this();
        this.firstname = firstname;
        this.surname = surname;
        this.gender = gender;
        this.birthDate = LocalDate.parse(birthDate, formatter);
        this.cart = cart;
    }

}
