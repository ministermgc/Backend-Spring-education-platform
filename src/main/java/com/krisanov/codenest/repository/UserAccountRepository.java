package com.krisanov.codenest.repository;

import com.krisanov.codenest.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    /**
     * Find a UserAccount by username.
     *
     * @param username the username of the UserAccount.
     * @return an Optional of UserAccount.
     */
    Optional<UserAccount> findByUsername(String username);
}
