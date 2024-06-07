package com.krisanov.codenest.task.service;

import com.krisanov.codenest.task.dto.PageTaskResponse;
import com.krisanov.codenest.task.dto.TaskRequest;
import com.krisanov.codenest.task.dto.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {

    /**
     * Finds a task by its ID.
     *
     * @param taskId the ID of the task to find
     * @return the task response containing the task details
     */
    TaskResponse findById(Long taskId);

    /**
     * Saves a new task.
     *
     * @param taskRequest the request object containing task details
     * @return the task response containing the saved task details
     */
    TaskResponse save(TaskRequest taskRequest);

    /**
     * Updates an existing task by its ID.
     *
     * @param taskId the ID of the task to update
     * @param taskRequest the request object containing updated task details
     * @return the task response containing the updated task details
     */
    TaskResponse updateById(Long taskId, TaskRequest taskRequest);

    /**
     * Deletes a task by its ID.
     *
     * @param taskId the ID of the task to delete
     */
    void deleteById(Long taskId);

    /**
     * Finds all tasks by lesson ID with pagination.
     *
     * @param lessonId the ID of the lesson
     * @param pageable the pagination information
     * @return a page of task responses containing the tasks for the specified lesson
     */
    Page<PageTaskResponse> findAllByLessonId(Long lessonId, Pageable pageable);
}
