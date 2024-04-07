package com.PFE.EndOfYearProject.Repository;

import com.PFE.EndOfYearProject.models.Numbers;
import com.PFE.EndOfYearProject.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface NumberRepository  {
    //Optional<Numbers> findByNumber(long number);
}
