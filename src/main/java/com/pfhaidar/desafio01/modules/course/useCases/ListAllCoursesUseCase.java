package com.pfhaidar.desafio01.modules.course.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;
import com.pfhaidar.desafio01.modules.course.repository.ICourseRepository;

@Service
public class ListAllCoursesUseCase {
    @Autowired
    private ICourseRepository courseRepository;

    public List<CourseEntity> execute() {
        return this.courseRepository.findAll();
    }
}
