package com.PFE.EndOfYearProject.Repository;

import com.PFE.EndOfYearProject.models.Users;
import com.PFE.EndOfYearProject.models.roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<roles,Long> {
    roles findByName(String name);
}
