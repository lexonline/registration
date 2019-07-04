package com.lexrizen.UserRegistration.db.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Set;


@Entity
public class User {

    // Unique identifier
    @Id
    @Column(nullable=false, unique=true, length=30)
    private String id;

    // User's first name
    @Column(nullable=false, length=64)
    private String firstName;

    // User's last name
    @Column(nullable=false, length=64)
    private String lastName;

    // User's e-mail address
    @Column(nullable=false, unique=true, length=64)
    private String emailAddress;

    // User's password (should be hashed!)
    @JsonIgnore
    @Column(nullable=false, length=256)
    private String password;

    @Column(nullable=false,unique=true, length=20)
    private String phone;

    @Column(length=4000)
    private String address;

    @Column(nullable=false)
    private Double salary;
    
    @Column(nullable=false, length=20)
    private String membertype;

    
    /**
     * Constructs a new User.
     */
    public User() {
    }

    /**
     * Tests equality of <code>User</code> objects.
     *
     * @param obj Another <code>User</code>
     * @return <code>true</code> if, and only if, <code>the this.id == obj.id</code>
     */
    @Override
    public boolean equals(Object obj) {
        if ( obj == null ) return false;

        if ( obj instanceof User ) {
            User other = (User) obj;
            return this.getId() == other.getId();
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.getId()), this.getEmailAddress());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", this.getId())
                .add("emailAddress", this.getEmailAddress())
                .add("firstName", this.getFirstName())
                .add("lastName", this.getLastName())
                .toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getMembertype() {
        return membertype;
    }

    public void setMembertype(String membertype) {
        this.membertype = membertype;
    }

}