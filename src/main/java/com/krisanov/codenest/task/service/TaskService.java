package com.krisanov.codenest.task.service;

import com.krisanov.codenest.task.dto.PageTaskResponse;
import com.krisanov.codenest.task.dto.TaskRequest;
import com.krisanov.codenest.task.dto.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    TaskResponse findById(Long taskId);

    TaskResponse save(TaskRequest taskRequest);

    TaskResponse updateById(Long taskId, TaskRequest taskRequest);

    void deleteById(Long taskId);

    Page<PageTaskResponse> findAllByLessonId(Long lessonId, Pageable pageable);
}
