package com.clustag.tasks.dto;

import com.clustag.tasks.model.TaskState;

public record TaskResponse(Long id, String name, TaskState state) {}