package com.PFE.EndOfYearProject.Services.impl;

import com.PFE.EndOfYearProject.Repository.ContactRepository;
import com.PFE.EndOfYearProject.Repository.UserRepository;
import com.PFE.EndOfYearProject.Services.ContactService;
import com.PFE.EndOfYearProject.dto.ContactDto;
import com.PFE.EndOfYearProject.models.Contacts;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.PFE.EndOfYearProject.Mapper.ContactMapper.mapToContact;
import static com.PFE.EndOfYearProject.Mapper.ContactMapper.mapToContactDto;

@Service
public class ContactServiceImpl implements ContactService {
    private ContactRepository contactRepository;
    private UserRepository userRepository;

    public ContactServiceImpl(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ContactDto> findAllContact() {
        List<Contacts> contacts=contactRepository.findAll();
        return contacts.stream().map((contact) -> mapToContactDto(contact)).collect(Collectors.toList());
    }



    @Override
    public Contacts saveContact(ContactDto contactDto) {
        Contacts contacts = mapToContact(contactDto);

        return contactRepository.save(contacts);
    }

    @Override
    public ContactDto findContactById(long ContactId) {
        return null;
    }

    @Override
    public void updateContact(ContactDto contact) {

    }

    @Override
    public void delete(Long contactId) {

    }

    @Override
    public List<ContactDto> searchClubs(String query) {
        return null;
    }
}
