package com.krisanov.codenest.task.mapper;

import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.task.dto.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {

    /**
     * Mapping a Task entity to a TaskResponse DTO.
     *
     * @param task the Task entity to convert
     * @return the converted TaskResponse DTO
     */
    @Mapping(target = "taskId", source = "id")
    @Mapping(target = "lessonId", expression = "java(task.getLesson().getId())")
    TaskResponse toDto(Task task);
}
