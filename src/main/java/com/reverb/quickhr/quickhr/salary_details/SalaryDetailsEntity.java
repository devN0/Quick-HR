package com.reverb.quickhr.quickhr.salary_details;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "salary_details")
public class SalaryDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float baseSalary;
    private Float hra;
    private LocalDate issueDate;
}
