package com.clustag.tasks.controller;

import com.clustag.tasks.dto.TaskCreateRequest;
import com.clustag.tasks.dto.TaskResponse;
import com.clustag.tasks.dto.TaskUpdateRequest;
import com.clustag.tasks.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody TaskCreateRequest req){
        return service.create(req);
    }

    @GetMapping
    public List<TaskResponse> list(){
        return service.list();
    }

    @GetMapping("/{id}")
    public TaskResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody TaskUpdateRequest req){
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}