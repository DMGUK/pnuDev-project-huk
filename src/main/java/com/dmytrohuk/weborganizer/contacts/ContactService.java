package com.dmytrohuk.weborganizer.contacts;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    public Contact createContact(ContactCreateDTO createDTO){
        Contact contact = contactMapper.toContact(createDTO);
        return contactRepository.save(contact);
    }

    public ContactViewDTO viewContact(Long id){
        Contact existingContact = contactRepository.findById(id).orElseThrow(
            () -> new ContactNotFoundException(
                new Throwable("Contact with id " + id + " does not exist")
            )
        );
        return contactMapper.toViewDTO(existingContact);
    }

    @Transactional
    public ContactCreateDTO updateContact(Long id, ContactUpdateDTO updateDTO){
        Contact existingContact = contactRepository.findById(id).orElseThrow(
            () -> new ContactNotFoundException(
                new Throwable("Contact with id " + id + " does not exist")
            )
        );
        contactMapper.updateContact(updateDTO, existingContact);
        return contactMapper.toContactCreateDTO(contactRepository.save(existingContact));
    }

    public void deleteContact(Long id){
        Contact existingContact = contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException(
                        new Throwable("Contact with id " + id + " does not exist")
                )
        );
        contactRepository.deleteById(id);
    }
}
