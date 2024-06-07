package com.krisanov.codenest.repository;

import com.krisanov.codenest.domain.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonContentRepository extends JpaRepository<LessonContent, Long> {

    /**
     * Finds all content related to a specific Lesson.
     *
     * @param lessonId the ID of the Lesson for which LessonContent is to be retrieved
     * @return a List containing LessonContent
     */
    List<LessonContent> findAllByLessonId(Long lessonId);
}
