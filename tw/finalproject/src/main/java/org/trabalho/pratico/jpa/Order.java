package org.trabalho.pratico.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ORDER_TABLE")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String payment;
    private int quantity;
    private String name;
    private float price;
    private String username;

//    //@Column(nullable = false)
//    private String payment;
//    //@Column(nullable = false)
//    private int quantity;
//    //@Column(nullable = false)
//    private String name;
////    @Column(nullable = false)
//    private float price;
//    //@Column(nullable = false)
//    //private List<Product> productList;

//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;

    protected Order() {}

    public Order(String payment, int quatity, String name,float price, String username) {
        this.payment = payment;
        this.quantity = quatity;
        this.name = name;
        this.price = price;
        this.username = username;
    }

    @Override
    public String toString() {
        return String.format(
                "Order[id=%d,username='%s' productName='%s', productPrice='%f' quantity='%d', payment='%s']",
                id, username, name, price,quantity, payment);
    }

    public Long getId() {
        return id;
    }

    public String getPayment(){
        return payment;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getName(){ return name; }

    public float getPrice(){ return price; }

    public String getUsername(){ return username;}

    //public List<Product> getProductList(){ return productList;}

//    public User getUser(){ return user;}
//
//    public void setUser(User user){
//        this.user = user;
//    }

}
