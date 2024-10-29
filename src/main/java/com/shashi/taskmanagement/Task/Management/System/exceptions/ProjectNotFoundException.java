package com.shashi.taskmanagement.Task.Management.System.exceptions;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message) {
        super(message);
    }
    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public static void throwNotFound(String projectId) {
        throw new ProjectNotFoundException("Project not found with id: " + projectId);
    }
    public static void throwCreationFailed(String message, Throwable cause) {
        throw new ProjectNotFoundException("Failed to create project: " + message, cause);
    }
}

