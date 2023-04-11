package com.example.api_angular.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataCustomer {
    @Id
    @NotNull
    @GeneratedValue
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
    private Date birthdate;

    @Column(name="username", length=15)
    private String username;

    @Column(name="email", length=30)
    private String email;

    @Column(name="password", length=16)
    private String password;
}
