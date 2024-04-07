package com.PFE.EndOfYearProject.Mapper;

import com.PFE.EndOfYearProject.dto.ContactDto;
import com.PFE.EndOfYearProject.models.Contacts;

public class ContactMapper {
    public static Contacts mapToContact(ContactDto contactDto){
        Contacts contact=Contacts.builder()
                .id(contactDto.getId())
                .num(contactDto.getNum())
                .address(contactDto.getAddress())
                .username(contactDto.getUsername())

                .build();
        return contact;
    }
    public static ContactDto mapToContactDto(Contacts contact){
        ContactDto contactDto=ContactDto.builder()
                .id(contact.getId())
                .num(contact.getNum())
                .address(contact.getAddress())
                .username(contact.getUsername())
                .build();
        return contactDto;}

}
