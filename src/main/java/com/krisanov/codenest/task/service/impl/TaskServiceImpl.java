package com.krisanov.codenest.task.service.impl;

import com.krisanov.codenest.common.exception.NotFoundException;
import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.repository.LessonRepository;
import com.krisanov.codenest.repository.TaskRepository;
import com.krisanov.codenest.task.dto.PageTaskResponse;
import com.krisanov.codenest.task.dto.TaskRequest;
import com.krisanov.codenest.task.dto.TaskResponse;
import com.krisanov.codenest.task.mapper.PageTaskResponseMapper;
import com.krisanov.codenest.task.mapper.TaskRequestMapper;
import com.krisanov.codenest.task.mapper.TaskResponseMapper;
import com.krisanov.codenest.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final LessonRepository lessonRepository;

    private final TaskRequestMapper taskRequestMapper;

    private final TaskResponseMapper taskResponseMapper;

    private final PageTaskResponseMapper pageTaskResponseMapper;

    @Override
    public TaskResponse findById(Long taskId) {
        return taskRepository
                .findById(taskId)
                .map(taskResponseMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                        "Task with id %d is not found".formatted(taskId)));
    }

    @Override
    public TaskResponse save(TaskRequest taskRequest) {
        Task task = taskRequestMapper.toEntity(taskRequest);
        setLessonToTask(taskRequest.lessonId(), task);
        return taskResponseMapper.toDto(taskRepository.save(task));
    }

    @Override
    public TaskResponse updateById(Long taskId, TaskRequest taskRequest) {
        return taskRepository
                .findById(taskId)
                .map(task -> {
                    Task updatedTask = taskRequestMapper.update(taskRequest, task);
                    setLessonToTask(taskRequest.lessonId(), updatedTask);
                    return taskResponseMapper.toDto(taskRepository.save(updatedTask));
                })
                .orElseThrow(() -> new NotFoundException(
                        "Task with id %d is not found".formatted(taskId)));
    }

    @Override
    public void deleteById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    @Override
    public Page<PageTaskResponse> findAllByLessonId(Long lessonId, Pageable pageable) {
        return taskRepository
                .findAllByLessonId(lessonId, pageable)
                .map(pageTaskResponseMapper::toDto);
    }

    private void setLessonToTask(Long lessonId, Task task) {
        task.setLesson(lessonRepository
                .findById(lessonId)
                .orElseThrow(() -> new NotFoundException(
                        "Lesson with id %d is not found".formatted(lessonId))));
    }
}
