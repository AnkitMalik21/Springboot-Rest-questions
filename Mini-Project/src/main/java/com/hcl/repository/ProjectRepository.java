package com.hcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hcl.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
     @Query("Select p from Project p where p.projectType = :name")
     List<Project> findByProjectType(@Param("name") String name);
     List<Project> findByProjectTypeIgnoreCase(String projectType);
}
