package com.pfhaidar.desafio01.modules.course.useCases;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;
import com.pfhaidar.desafio01.modules.course.repository.ICourseRepository;

@Service
public class FindCourseByNameAndCategoryUseCase {
    
    @Autowired
    private ICourseRepository courseRepository;

    public Optional<List<CourseEntity>> execute(String name, String category){
        return this.courseRepository.findByNameAndCategory(name, category);
    }
}
