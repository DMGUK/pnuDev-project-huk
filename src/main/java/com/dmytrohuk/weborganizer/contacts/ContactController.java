package com.dmytrohuk.weborganizer.contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public void createNewNote(@RequestBody ContactCreateDTO contactDTO){
        contactService.createContact(contactDTO);
    }

    @GetMapping(path = "{contact-id}")
    public ContactViewDTO getContactById(@PathVariable("contact-id") Long contactId){
        return contactService.viewContact(contactId);
    }

    @PutMapping(path = "{contact-id}")
    public void updateContact(@PathVariable("contact-id") Long contactId, @RequestBody ContactUpdateDTO updateDTO){
        contactService.updateContact(contactId, updateDTO);
    }

    @DeleteMapping(path = "{contact-id}")
    public void deleteContact(@PathVariable("contact-id") Long contactId){
        contactService.deleteContact(contactId);
    }
}
