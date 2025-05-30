package com.losung.projectmanager.repository;

import com.losung.projectmanager.model.Task;
import com.losung.projectmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
    List<Task> findByResponsable(String responsable);

    @Query("SELECT t FROM Task t ORDER BY t.project.id")
    List<Task> findAllOrderByProjectId();

    default Map<Long, List<Task>> findAllGroupedByProject() {
        return findAllOrderByProjectId().stream()
                .collect(Collectors.groupingBy(task -> task.getProject().getId()));
    }
} 