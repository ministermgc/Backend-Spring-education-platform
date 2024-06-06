package com.krisanov.codenest.task.mapper;

import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.task.dto.TaskRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskRequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    Task toEntity(TaskRequest taskRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    Task update(TaskRequest taskRequest, @MappingTarget Task task);
}
