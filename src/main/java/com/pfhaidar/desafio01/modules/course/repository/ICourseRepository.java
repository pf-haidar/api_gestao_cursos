package com.pfhaidar.desafio01.modules.course.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfhaidar.desafio01.modules.course.entities.CourseEntity;

public interface ICourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> findById(UUID id);
    Optional<CourseEntity> findByName(String name);
    Optional<List<CourseEntity>> findByCategory(String category);
    Optional<List<CourseEntity>> findByNameAndCategory(String name, String category);
}
