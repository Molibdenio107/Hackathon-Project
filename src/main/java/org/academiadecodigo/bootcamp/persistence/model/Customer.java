package org.academiadecodigo.bootcamp.persistence.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The customer model entity
 */
@Entity
@Table(name = "customers")
public class Customer extends AbstractModel {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Integer phone;

    @OneToMany(
            // propagate changes on customer entity to account entities
            cascade = {CascadeType.ALL},

            // make sure to remove addresses if unlinked from customer
            orphanRemoval = true,

            // user customer foreign key on account table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "customer",

            // fetch addresses from database together with user
            fetch = FetchType.EAGER
    )
    private List<Addresses> addresses = new ArrayList<>();


    /**
     *
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     */
    public Integer getPhone() {
        return phone;
    }

    /**
     *
     */
    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /**
     *
     */
    public List<Addresses> getAddresses() {
        return addresses;
    }

    /**
     *
     */
    public void setAddresses(List<Addresses> addresses) {
        this.addresses = addresses;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {

        // printing sales with lazy loading
        // and no session will cause issues
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", addresses=" + addresses +
                "} " + super.toString();
    }
}



