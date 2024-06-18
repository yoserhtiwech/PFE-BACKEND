package com.PFE.EndOfYearProject.Repository;

import com.PFE.EndOfYearProject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
   //  Optional<Users> findByName(String url);
     Optional<Users> findByEmail(String email);
     Users findByUsername(String username);

     @Query("SELECT u FROM Users u WHERE u.username LIKE CONCAT('%', :query, '%')")
     List<Users> searchUsers(String query);

    Users findFirstByUsername(String username);
}
