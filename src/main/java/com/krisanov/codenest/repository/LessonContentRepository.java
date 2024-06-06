package com.krisanov.codenest.repository;

import com.krisanov.codenest.domain.LessonContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonContentRepository extends JpaRepository<LessonContent, Long> {

}
