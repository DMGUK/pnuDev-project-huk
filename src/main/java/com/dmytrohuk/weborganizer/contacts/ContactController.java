package com.dmytrohuk.weborganizer.contacts;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contacts")
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @PostMapping
    public void createNewNote(@RequestBody ContactCreateDTO contactDTO){
        contactService.createContact(contactDTO);
    }

    @GetMapping(path = "{id}")
    public ContactViewDTO getContactById(@PathVariable("id") Long contactId){
        return contactService.viewContact(contactId);
    }

    @PutMapping(path = "{id}")
    public void updateContact(@PathVariable("id") Long contactId, @RequestBody ContactUpdateDTO updateDTO){
        contactService.updateContact(contactId, updateDTO);
    }

    @DeleteMapping(path = "{id}")
    public void deleteContact(@PathVariable("id") Long contactId){
        contactService.deleteContact(contactId);
    }
}
