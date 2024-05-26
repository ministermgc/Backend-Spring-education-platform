package com.krisanov.codenest.lesson.repository;

import com.krisanov.codenest.lesson.domain.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParagraphRepository extends JpaRepository<Paragraph, Long> {

    /**
     * Retrieves all paragraphs associated with a specific lesson, identified by its id.
     *
     * @param lessonId The id of the lesson.
     * @return A list of all paragraphs associated with the lesson.
     */
    List<Paragraph> findAllByLessonId(Long lessonId);
}
