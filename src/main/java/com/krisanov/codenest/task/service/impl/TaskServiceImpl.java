package com.krisanov.codenest.task.service.impl;

import com.krisanov.codenest.common.exception.NotFoundException;
import com.krisanov.codenest.domain.Task;
import com.krisanov.codenest.repository.LessonRepository;
import com.krisanov.codenest.repository.TaskRepository;
import com.krisanov.codenest.task.domain.SolutionStatus;
import com.krisanov.codenest.task.dto.PageTaskResponse;
import com.krisanov.codenest.task.dto.TaskRequest;
import com.krisanov.codenest.task.dto.TaskResponse;
import com.krisanov.codenest.task.dto.TaskSolutionRequest;
import com.krisanov.codenest.task.dto.TaskSolutionResponse;
import com.krisanov.codenest.task.mapper.PageTaskResponseMapper;
import com.krisanov.codenest.task.mapper.TaskRequestMapper;
import com.krisanov.codenest.task.mapper.TaskResponseMapper;
import com.krisanov.codenest.task.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Implementation of the TaskService interface for managing tasks.
 *
 * @see TaskService
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final LessonRepository lessonRepository;

    private final TaskRequestMapper taskRequestMapper;

    private final TaskResponseMapper taskResponseMapper;

    private final PageTaskResponseMapper pageTaskResponseMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse findById(Long taskId) {
        return taskRepository
                .findById(taskId)
                .map(taskResponseMapper::toDto)
                .orElseThrow(() -> new NotFoundException(
                        "Task with id %d is not found".formatted(taskId)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse save(TaskRequest taskRequest) {
        Task task = taskRequestMapper.toEntity(taskRequest);
        setLessonToTask(taskRequest.lessonId(), task);
        return taskResponseMapper.toDto(taskRepository.save(task));
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteById(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PageTaskResponse> findAllByLessonId(Long lessonId, Pageable pageable) {
        return taskRepository
                .findAllByLessonId(lessonId, pageable)
                .map(pageTaskResponseMapper::toDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskSolutionResponse checkUserTaskAnswer(Long taskId, TaskSolutionRequest taskSolutionRequest) {
        Task task = taskRepository
                .findById(taskId)
                .orElseThrow(() -> new NotFoundException(
                        "Task with id %d is not found".formatted(taskId)));

        SolutionStatus solutionStatus = SolutionStatus.FAILURE;
        if (task.getAnswer().equals(taskSolutionRequest.userTaskAnswer())) {
            solutionStatus = SolutionStatus.SUCCESS;
        }

        return TaskSolutionResponse.builder()
                .solutionStatus(solutionStatus)
                .build();
    }

    /**
     * Sets the lesson to the task.
     *
     * @param lessonId the ID of the lesson
     * @param task     the task to set the lesson to
     * @throws NotFoundException if the lesson with the given ID is not found
     */
    private void setLessonToTask(Long lessonId, Task task) {
        task.setLesson(lessonRepository
                .findById(lessonId)
                .orElseThrow(() -> new NotFoundException(
                        "Lesson with id %d is not found".formatted(lessonId))));
    }
}
