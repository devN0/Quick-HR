package com.reverb.quickhr.quickhr.leave_details;

import javax.persistence.*;

@Entity
@Table(name = "leave_details")
public class LeaveDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
