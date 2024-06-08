package com.krisanov.codenest.task.mapper;

import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.task.dto.PageTaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PageTaskResponseMapper {

    /**
     * Mapping a Task entity to a PageTaskResponse DTO.
     *
     * @param task the Task entity to convert
     * @return the converted PageTaskResponse DTO
     */
    @Mapping(target = "taskId", source = "id")
    PageTaskResponse toDto(Task task);
}
