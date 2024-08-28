package com.PFE.EndOfYearProject.Repository;

import com.PFE.EndOfYearProject.models.Users;
import com.PFE.EndOfYearProject.models.roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<roles,Integer> {
    Optional<roles> findByName(String roleStudent);
}
