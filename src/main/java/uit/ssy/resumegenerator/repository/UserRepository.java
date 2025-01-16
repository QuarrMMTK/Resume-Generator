package uit.ssy.resumegenerator.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uit.ssy.resumegenerator.models.User;

import java.util.Optional;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 13:22
 * Project Name : ResumeGenerator
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);

    @Transactional
    @Modifying
    @Query("UPDATE User u " +
            "SET u.enabled = TRUE WHERE u.userName = ?1")
    void enableAppUser(String userName);
}
