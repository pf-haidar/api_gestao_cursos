package com.pfhaidar.desafio01.modules.course.useCases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;
import com.pfhaidar.desafio01.modules.course.repository.ICourseRepository;

@Service
public class FindCourseByNameUseCase {

    @Autowired
    private ICourseRepository courseRepository;

    public Optional<CourseEntity> execute(String name) {
        return this.courseRepository.findByName(name);
    }
}
