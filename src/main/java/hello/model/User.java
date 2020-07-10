package hello.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.print.Book;
import java.util.Set;

@Document(collection = "user")
public class User {
    @Id
    private String id;

//    @DBRef
//    private Customer customer;

    @DBRef
    private Set<Role> roles;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    private String email;
    private String userName;
    private String password;
    private boolean enabled;

    public User(){}

//    public User(Customer customer) {
//        this.customer = customer;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

//    public Customer getCustomer() {
//        return customer;
//    }

//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles () {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}