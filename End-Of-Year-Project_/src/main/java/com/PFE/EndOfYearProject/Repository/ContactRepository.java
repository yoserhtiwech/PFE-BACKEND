package com.PFE.EndOfYearProject.Repository;

import com.PFE.EndOfYearProject.models.Contacts;
import com.PFE.EndOfYearProject.models.Numbers;
import com.PFE.EndOfYearProject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contacts,Long> {
    Optional<Contacts> findByNum(String number);

    @Query("SELECT c from Contacts c WHERE CAST(c.num AS string) LIKE CONCAT('%', :query, '%')")
    List<Contacts> searchClubs(String query);

}
