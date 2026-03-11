package com.clustag.tasks.scheduler;

import com.clustag.tasks.service.TaskService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskStateScheduler{
    private final TaskService taskService;

    public TaskStateScheduler(TaskService taskService){
        this.taskService = taskService;
    }

    @Scheduled(fixedDelay = 15000)
    public void tick(){
        taskService.applyStateTransitions();
    }
}