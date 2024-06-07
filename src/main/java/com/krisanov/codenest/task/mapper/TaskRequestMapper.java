package com.krisanov.codenest.task.mapper;

import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.task.dto.TaskRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskRequestMapper {

    /**
     * Mapping a TaskRequest DTO to a Task entity.
     *
     * @param taskRequest the TaskRequest DTO to convert
     * @return the converted Task entity
     * @see Task
     * @see TaskRequest
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    Task toEntity(TaskRequest taskRequest);

    /**
     * Updates a Task entity with values from a TaskRequest DTO.
     *
     * @param taskRequest the TaskRequest DTO with updated values
     * @param task the Task entity to update
     * @return the updated Task entity
     * @see Task
     * @see TaskRequest
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lesson", ignore = true)
    Task update(TaskRequest taskRequest, @MappingTarget Task task);
}
