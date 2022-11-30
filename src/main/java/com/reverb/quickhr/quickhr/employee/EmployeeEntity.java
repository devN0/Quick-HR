package com.reverb.quickhr.quickhr.employee;

import com.reverb.quickhr.quickhr.leave_details.LeaveDetailsEntity;
import com.reverb.quickhr.quickhr.salary_details.SalaryDetailsEntity;
import com.reverb.quickhr.quickhr.user.UserEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends UserEntity {
    @OneToOne
    private SalaryDetailsEntity salaryDetailsEntity;
    @OneToOne
    private LeaveDetailsEntity leaveDetailsEntity;
}
