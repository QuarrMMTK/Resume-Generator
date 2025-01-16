package uit.ssy.resumegenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uit.ssy.resumegenerator.models.UserProfile;

import java.util.Optional;

/**
 * Author : Min Myat Thu Kha
 * Created At : 15/01/2025, Jan , 13:23
 * Project Name : ResumeGenerator
 **/
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
    Optional<UserProfile> findByUserName(String userName);
}
