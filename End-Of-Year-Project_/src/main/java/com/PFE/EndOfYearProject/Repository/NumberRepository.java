package com.PFE.EndOfYearProject.Repository;

import com.PFE.EndOfYearProject.models.Numbers;
import com.PFE.EndOfYearProject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface NumberRepository extends JpaRepository<Numbers,Integer> {
    List<Numbers> findAll() ;
    Optional<Numbers> findByuser_id(long ID);

}
