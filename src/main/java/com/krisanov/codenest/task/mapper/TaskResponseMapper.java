package com.krisanov.codenest.task.mapper;

import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.task.dto.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {

    @Mapping(target = "taskId", source = "id")
    @Mapping(target = "lessonId", expression = "java(task.getLesson().getId())")
    TaskResponse toDto(Task task);
}
