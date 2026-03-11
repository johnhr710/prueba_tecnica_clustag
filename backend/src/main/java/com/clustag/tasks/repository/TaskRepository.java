package com.clustag.tasks.repository;

import com.clustag.tasks.model.Task;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, Long>{
    @Query("SELECT t FROM Task t WHERE t.state = com.clustag.tasks.model.TaskState.CREATED and t.createdAt <= :threshold")
    List<Task> findCreatedOlderThan(Instant threshold);

    @Query("SELECT t FROM Task t WHERE t.state = com.clustag.tasks.model.TaskState.RUNNING AND t.runningAt IS NOT NULL AND t.runningAt <= :threshold")
    List<Task> findRunningOlderThan(Instant threshold);

}