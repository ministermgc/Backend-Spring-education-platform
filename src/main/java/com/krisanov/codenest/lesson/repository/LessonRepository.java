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
   * This method retrieves a feed of lessons ordered by their id in descendant order,
   * using a specified pagination.
   *
   * @param pageable - Pagination information.
   * @return A paged list of Lessons, ordered by id in descendant order.
   */
  @Query("SELECT l FROM Lesson l ORDER BY l.id DESC")
  Page<Lesson> findFeed(Pageable pageable);
}
