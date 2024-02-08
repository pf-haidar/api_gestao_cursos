package com.pfhaidar.desafio01.exceptions;

public class CourseFoundException extends RuntimeException {
    public CourseFoundException() {
        super("Curso já existe.");
    }
}
