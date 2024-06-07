package com.krisanov.codenest.repository;

import com.krisanov.codenest.domain.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Finds all tasks related to a specific Lesson, and returns them in a pagination list.
     *
     * @param lessonId the ID of the Lesson for which Tasks are to be retrieved
     * @param pageable the Pageable object containing pagination info
     * @return a Page containing Tasks
     */
    Page<Task> findAllByLessonId(Long lessonId, Pageable pageable);
}
