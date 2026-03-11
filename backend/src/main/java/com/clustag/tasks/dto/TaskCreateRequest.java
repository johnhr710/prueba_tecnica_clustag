package com.clustag.tasks.dto;

import jakarta.validation.constraints.NotBlank;

public record TaskCreateRequest(@NotBlank String name) {}