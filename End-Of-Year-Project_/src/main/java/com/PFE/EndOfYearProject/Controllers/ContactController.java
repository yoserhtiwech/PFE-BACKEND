package com.PFE.EndOfYearProject.Controllers;
import com.PFE.EndOfYearProject.Services.ContactService;
import com.PFE.EndOfYearProject.dto.ContactDto;
import com.PFE.EndOfYearProject.models.Contacts;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Contact")
@RequiredArgsConstructor
public class ContactController {
    private ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @GetMapping("/contacts")
    public String listcontact(Model model){
        List<ContactDto> contacts = contactService.findAllContact();
        model.addAttribute("contacts",contacts);
        return "contacts-list";
    }
    @GetMapping("/contacts/{contactId}")
    public String contactDetail(@PathVariable("contactId") long contactId, Model model){
        ContactDto contactDto  =contactService.findContactById(contactId);
        model.addAttribute("contact",contactDto);
        return "contact-detail";

    }
    @GetMapping("/contacts/new")
    public String CreateContactForm(Model model){
        Contacts contact= new Contacts();
        model.addAttribute("contact",contact);
        return"contacts-create";
    }
    @GetMapping("/contacts/{contactId}/delete")
    public String deleteContact(@PathVariable("contactId") long contactId){
        contactService.delete(contactId);
        return "redirect:/contacts";
    }

}
