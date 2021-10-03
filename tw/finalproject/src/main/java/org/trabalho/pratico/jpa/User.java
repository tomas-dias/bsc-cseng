package org.trabalho.pratico.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String role;

//    @OneToMany(mappedBy = "user")
//    private List<Order> orders;

    protected User() {}

    public User(String firstName, String lastName, String email, String username, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format(
                "Client[id=%d, firstName='%s', lastName='%s', email='%s', username='%s', password='%s', role='%s']",
                id, firstName, lastName, email, username, password, role);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role;
    }

    //public List<Order> getOrders(){ return orders;}

//    public void setOrders(List<Order> orders){
//        this.orders = orders;
//        for(Order o:orders){
//            o.setUser(this);
//        }
//    }

}

