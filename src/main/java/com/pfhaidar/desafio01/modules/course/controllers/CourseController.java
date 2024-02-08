package com.pfhaidar.desafio01.modules.course.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;
import com.pfhaidar.desafio01.modules.course.useCases.CreateCourseUseCase;
import com.pfhaidar.desafio01.modules.course.useCases.DeleteCourseUseCase;
import com.pfhaidar.desafio01.modules.course.useCases.FindCourseByCategoryUseCase;
import com.pfhaidar.desafio01.modules.course.useCases.FindCourseByNameAndCategoryUseCase;
import com.pfhaidar.desafio01.modules.course.useCases.FindCourseByNameUseCase;
import com.pfhaidar.desafio01.modules.course.useCases.ListAllCoursesUseCase;
import com.pfhaidar.desafio01.modules.course.useCases.UpdateCourseByIdUseCase;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cursos")
public class CourseController {

    @Autowired
    private CreateCourseUseCase createCourseUseCase;

    @Autowired
    private ListAllCoursesUseCase listAllCoursesUseCase;

    @Autowired
    private UpdateCourseByIdUseCase updateCourseByIdUseCase;

    @Autowired
    private FindCourseByNameUseCase findCourseByNameUseCase;

    @Autowired
    private FindCourseByCategoryUseCase findCourseByCategoryUseCase;

    @Autowired
    private FindCourseByNameAndCategoryUseCase findCourseByNameAndCategoryUseCase;

    @Autowired
    private DeleteCourseUseCase deleteCourseUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity) {
        try {
            var result = this.createCourseUseCase.execute(courseEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> findCourses(@RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "category", required = false) String category) {
        try {
            Optional<List<CourseEntity>> resultList = null;

            if (name == null && category == null) {
                var list = this.listAllCoursesUseCase.execute();
                return ResponseEntity.ok().body(list);
            } else if (name != null && category != null) {
                resultList = this.findCourseByNameAndCategoryUseCase.execute(name, category);
            } else if (category != null) {
                resultList = this.findCourseByCategoryUseCase.execute(category);
            } else {
                var result = this.findCourseByNameUseCase.execute(name);
                return ResponseEntity.ok().body(result);
            }
            return ResponseEntity.ok().body(resultList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody CourseEntity courseEntity, @PathVariable UUID id) {
        try {
            var course = this.updateCourseByIdUseCase.execute(courseEntity, id);
            return ResponseEntity.ok().body(course);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            this.deleteCourseUseCase.execute(id);
            return ResponseEntity.ok().body("deletado!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
