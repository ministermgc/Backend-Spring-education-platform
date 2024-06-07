package com.krisanov.codenest.repository;

import com.krisanov.codenest.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    /**
     * Find a Role by authority.
     *
     * @param authority the authority of the Role.
     * @return an Optional of Role.
     */
    Optional<Role> findByAuthority(String authority);
}
