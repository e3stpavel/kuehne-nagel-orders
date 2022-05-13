package com.example.e3stpavel.knorders.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "registration_code", nullable = false)
    private String registrationCode;

    @Column(name = "email", nullable = false)
    @Email
    private String email;

    @Column(name = "phone", nullable = false)
    @Pattern(regexp="^(\\\\+\\\\d{1,3}( )?)?(\\\\d{3}[ ]?)(\\\\d{2}[ ]?){2}\\\\d{2}$")
    private String phone;
}
