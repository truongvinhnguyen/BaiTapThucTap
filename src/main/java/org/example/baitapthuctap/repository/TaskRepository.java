package org.example.baitapthuctap.repository;

import org.example.baitapthuctap.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUserId(Integer userId);

    List<Task> findByProjectId(Integer projectId);
}
