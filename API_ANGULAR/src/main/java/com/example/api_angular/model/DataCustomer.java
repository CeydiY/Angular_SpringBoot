package com.example.api_angular.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="clients")
public class DataCustomer {
    @Id
    @GeneratedValue
    @NotNull
    private Integer id;

    @Column(name="firstName", length=50)
    private String firstName;

    @Column(name="lastName", length=50)
    private String lastName;

    @Column(name="name", length=50)
    private String name;

    @Column(name="age")
    private Integer age;

    @Column(name="address", length=100)
    private String address;

    @Column(name="gender", length=15)
    private String gender;

    @Column(name="country", length=15)
    private String country;

    @Column(name="birthdate")
    private String birthdate;

    @Column(name="username", length=15)
    private String username;

    @Column(name="email", length=30)
    private String email;

    @Column(name="password", length=16)
    private String password;
}
