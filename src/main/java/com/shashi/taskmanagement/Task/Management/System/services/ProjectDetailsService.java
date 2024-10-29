package com.shashi.taskmanagement.Task.Management.System.services;


import com.shashi.taskmanagement.Task.Management.System.dto.EmailMessage;
import com.shashi.taskmanagement.Task.Management.System.dto.ProjectDetailsRecord;
import com.shashi.taskmanagement.Task.Management.System.dto.ProjectDetailsResponse;
import com.shashi.taskmanagement.Task.Management.System.exceptions.ProjectNotFoundException;
import com.shashi.taskmanagement.Task.Management.System.model.ProjectDetails;
import com.shashi.taskmanagement.Task.Management.System.repository.EmployeeRepository;
import com.shashi.taskmanagement.Task.Management.System.repository.ProjectDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectDetailsService {
 private final ProjectDetailsRepository projectDetailsRepository;
 private final EmployeeRepository employeeRepository;
 @Autowired
 private KafkaProducerService kafkaProducerService;
 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

public void createProject(ProjectDetailsRecord projectDetailsRecord){
 ProjectDetails projectDetails =new ProjectDetails();

 projectDetails.setProject_name(projectDetailsRecord.project_name());
 projectDetails.setProject_head(projectDetailsRecord.project_head());
 projectDetails.setStart_date(projectDetailsRecord.start_date());
 projectDetails.setEnd_date(projectDetailsRecord.end_date());
 projectDetails.setCreated_on(projectDetailsRecord.created_on());
 projectDetails.setCreated_by(projectDetailsRecord.created_by());
 projectDetails.setModified_on(projectDetailsRecord.modified_on());
 projectDetails.setModified_by(projectDetailsRecord.modified_by());
 try{
  projectDetailsRepository.save(projectDetails);
  String projectHeadName = projectDetailsRecord.project_head();
  String projectManagerMail = employeeRepository.findEmailByName(projectHeadName);
  if (projectManagerMail != null) {
   EmailMessage emailMessage = new EmailMessage(
           projectManagerMail,
           "Project Created Notification",
           "Dear " + projectHeadName + ",\n\nA new project has been created. Please check the system for details."
   );
   kafkaProducerService.sendEmail(emailMessage);
  } else {
   log.info("Project Head not found: " + projectHeadName);
  }
 }catch (Exception e){
 log.info("Error while creating project" , e);
 }

}

public List<ProjectDetailsResponse>getAllProjectsData(){
return projectDetailsRepository.findAll()
        .stream()
        .map(projects ->new ProjectDetailsResponse(
                projects.getProject_id(),
                projects.getProject_name(),
                projects.getProject_head(),
                projects.getStart_date(),
                projects.getEnd_date(),
                projects.getCreated_on(),
                projects.getCreated_by(),
                projects.getModified_on(),
                projects.getModified_by()
        )).toList();
}

// private void sendEmailNotification(String to, ProjectDetails projectDetails) {
//  SimpleMailMessage message = new SimpleMailMessage();
//  message.setTo(to);
//  message.setSubject("New Project Created: " + projectDetails.getProject_name());
//  message.setText("Hello,\n\nA new project has been created:\n" +
//          "Project Name: " + projectDetails.getProject_name() + "\n" +
//          "Project Head: " + projectDetails.getProject_head() + "\n" +
//          "Start Date: " + projectDetails.getStart_date() + "\n" +
//          "End Date: " + projectDetails.getEnd_date() + "\n\n" +
//          "Best Regards,\nYour Project Management System");
//
//  emailSender.send(message);
//  System.out.println("Email sent to: " + to);
// }

}
