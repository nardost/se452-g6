package edu.depaul.g6.accounts.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "employee_management")
public class EmployeeProfile implements Serializable {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer employeeId;

        @Column(name = "full_name")
        private String name;

        @Column(name = "department")
        private String department;

        @Column(name = "email")
        private String email;

        @Column(name = "password")
        private String password;

}
