package com.shashi.taskmanagement.Task.Management.System.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "project_details")
public class ProjectDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  project_id;
    private String project_name;
    private String project_head;
    private Date start_date;
    private Date end_date;
    private LocalDateTime created_on;
    private String created_by;
    private LocalDateTime modified_on;
    private  String modified_by;
}
