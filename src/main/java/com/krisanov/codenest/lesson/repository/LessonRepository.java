package com.krisanov.codenest.lesson.repository;

import com.krisanov.codenest.lesson.domain.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    /**
     * Retrieves a page of lessons, ordered by their id's in descending order.
     *
     * @param pageable The pageable object that defines the size and number of pages to retrieve.
     * @return A page of lessons in descending order by id.
     */
    @Query("SELECT l FROM Lesson l ORDER BY l.id DESC")
    Page<Lesson> findFeed(Pageable pageable);
}
