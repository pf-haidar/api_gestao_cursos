package com.pfhaidar.desafio01.modules.course.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfhaidar.desafio01.exceptions.CourseNotFoundException;
import com.pfhaidar.desafio01.modules.course.repository.ICourseRepository;

@Service
public class DeleteCourseUseCase {

    @Autowired
    private ICourseRepository courseRepository;

    public void execute(UUID id) {
        boolean courseExist = this.courseRepository.findById(id).isPresent();

        if (courseExist) {
            this.courseRepository.deleteById(id);
        } else {
            throw new CourseNotFoundException();
        }

    }
}
