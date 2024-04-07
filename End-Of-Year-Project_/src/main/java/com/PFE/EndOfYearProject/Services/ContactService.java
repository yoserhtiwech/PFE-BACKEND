package com.PFE.EndOfYearProject.Services;

import com.PFE.EndOfYearProject.dto.ContactDto;
import com.PFE.EndOfYearProject.dto.UserDto;
import com.PFE.EndOfYearProject.models.Contacts;
import com.PFE.EndOfYearProject.models.Users;

import java.util.List;

public interface ContactService {
    List<ContactDto> findAllContact();
    Contacts saveContact(ContactDto contactDto);
    ContactDto findContactById(long ContactId );
    void updateContact(ContactDto contact);
    void delete(Long contactId);
    List<ContactDto> searchClubs(String query);
}
