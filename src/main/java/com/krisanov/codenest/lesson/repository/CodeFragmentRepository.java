package com.krisanov.codenest.lesson.repository;

import com.krisanov.codenest.lesson.domain.CodeFragment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeFragmentRepository extends JpaRepository<CodeFragment, Long> {

    /**
     * Retrieves all code fragments associated with a specific lesson, identified by its id.
     *
     * @param lessonId The id of the lesson.
     * @return A list of all code fragments associated with the lesson.
     */
    List<CodeFragment> findAllByLessonId(Long lessonId);
}
