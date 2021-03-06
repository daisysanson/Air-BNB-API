package hello.model;

import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@QueryEntity
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @DBRef
    private Set<Role> roles;

    @Indexed(unique = true, direction = IndexDirection.DESCENDING)
    @Field(value = "email")
    private String email;

    @Field(value = "userName")
    private String userName;

    @Field(value = "address")
    private String address;

    @Field(value = "Password]")
    private String password;

    @Field(value = "firstName")
    private String firstName;

    @Field(value = "lastName" )
    private String lastName;

    @Field(value = "enabled")
    private boolean enabled;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Set<Role> getRoles () {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }



}