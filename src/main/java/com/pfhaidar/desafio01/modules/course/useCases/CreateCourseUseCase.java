package com.pfhaidar.desafio01.modules.course.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfhaidar.desafio01.exceptions.CourseFoundException;
import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;
import com.pfhaidar.desafio01.modules.course.repository.ICourseRepository;

@Service
public class CreateCourseUseCase {

    @Autowired
    private ICourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity) {
        this.courseRepository
                .findByName(courseEntity.getName())
                .ifPresent((course) -> {
                    throw new CourseFoundException();
                });

        return this.courseRepository.save(courseEntity);
    }
}
