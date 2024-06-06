package com.krisanov.codenest.task.mapper;

import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.task.dto.PageTaskResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PageTaskResponseMapper {

    PageTaskResponse toDto(Task task);
}
