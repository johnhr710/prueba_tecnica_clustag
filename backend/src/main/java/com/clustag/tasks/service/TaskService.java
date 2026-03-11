package com.clustag.tasks.service;

import com.clustag.tasks.dto.TaskCreateRequest;
import com.clustag.tasks.dto.TaskResponse;
import com.clustag.tasks.dto.TaskUpdateRequest;
import com.clustag.tasks.model.Task;
import com.clustag.tasks.model.TaskState;
import com.clustag.tasks.repository.TaskRepository;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TaskService{
    private final TaskRepository repo;

    public TaskService(TaskRepository repo){
        this.repo = repo;
    }

    @Transactional
    public TaskResponse create(TaskCreateRequest req){
        Task t = new Task();
        t.setName(req.name());
        t.setState(TaskState.CREATED);
        Task saved = repo.save(t);

        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> list(){
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse get(Long id){
        Task t = repo.findById(id).orElseThrow(()-> new IllegalArgumentException("Task not found"));
        return toResponse(t);
    }

    @Transactional
    public TaskResponse update(Long id, TaskUpdateRequest req){
        Task t = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        t.setName(req.name());
        return toResponse(t);
    }

    @Transactional
    public void delete(Long id){
        if(!repo.existsById(id)) throw new IllegalArgumentException("Task not found");
        repo.deleteById(id);
    }

    @Transactional
    public int applyStateTransitions(){
        Instant now = Instant.now();

        Instant createThreshold = now.minus(Duration.ofMinutes(2));
        List<Task> toRunning = repo.findCreatedOlderThan(createThreshold);
        for( Task t: toRunning){
            t.setState(TaskState.RUNNING);
            t.setRunningAt(now);
        }

        Instant runningThreshold = now.minus(Duration.ofMinutes(8));
        List<Task> toDone = repo.findRunningOlderThan(runningThreshold);
        for(Task t: toDone){
            t.setState(TaskState.DONE);
        }

        return toRunning.size() + toDone.size();
    }

    private TaskResponse toResponse(Task t){
        return new TaskResponse(t.getId(), t.getName(), t.getState());
    }


}