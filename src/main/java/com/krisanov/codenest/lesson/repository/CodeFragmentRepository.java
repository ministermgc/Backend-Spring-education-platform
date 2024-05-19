package com.krisanov.codenest.lesson.repository;

import com.krisanov.codenest.lesson.domain.CodeFragment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeFragmentRepository extends JpaRepository<CodeFragment, Long> {

}
