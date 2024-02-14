package com.pfhaidar.desafio01.modules.course.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfhaidar.desafio01.exceptions.CourseNotFoundException;
import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;
import com.pfhaidar.desafio01.modules.course.repository.ICourseRepository;

@Service
public class PatchCourseStatusUseCase {

    @Autowired
    private ICourseRepository courseRepository;

    public String execute(UUID id) {
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException());

        if (course.getStatus() == CourseEntity.EnumCourseActive.ACTIVE) {
            course.setStatus(CourseEntity.EnumCourseActive.DISABLED);
            courseRepository.save(course);
            return "Curso desativado!";
        } else {
            course.setStatus(CourseEntity.EnumCourseActive.ACTIVE);
            courseRepository.save(course);
            return "Curso Ativado!";
        }

    }
}
