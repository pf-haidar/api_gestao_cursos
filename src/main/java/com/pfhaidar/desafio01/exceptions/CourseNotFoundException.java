package com.pfhaidar.desafio01.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Curso não encontrado!");
    }
}
