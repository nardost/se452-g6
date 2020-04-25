package edu.depaul.g6.accounts.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//@Table(name = "account_management")
//@Data
@Entity
@Data
@Table (name = "account_management")
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}