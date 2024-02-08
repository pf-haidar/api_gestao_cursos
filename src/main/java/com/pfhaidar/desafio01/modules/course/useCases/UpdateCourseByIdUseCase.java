package com.pfhaidar.desafio01.modules.course.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfhaidar.desafio01.exceptions.CourseNotFoundException;
import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;
import com.pfhaidar.desafio01.modules.course.repository.ICourseRepository;

@Service
public class UpdateCourseByIdUseCase {

    @Autowired
    private ICourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity, UUID id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() ->  new CourseNotFoundException());
        
        course.setName(courseEntity.getName());
        course.setCategory(courseEntity.getCategory());

        return courseRepository.save(course);
    }
}
