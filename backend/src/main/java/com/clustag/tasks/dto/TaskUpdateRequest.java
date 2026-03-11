package com.clustag.tasks.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskUpdateRequest(@NotBlank String name){}