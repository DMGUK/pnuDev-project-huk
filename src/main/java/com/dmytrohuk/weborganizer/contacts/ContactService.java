package com.dmytrohuk.weborganizer.contacts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    public ContactViewDTO createContact(ContactCreateDTO createDTO){
        Contact contact = contactMapper.toContact(createDTO);
        return contactMapper.toViewDTO(contactRepository.save(contact));
    }

    public ContactViewDTO viewContact(Long id){
        Contact existingContact = contactRepository.findById(id).orElseThrow(
            () -> new ContactNotFoundException("Contact with id %d does not exist".formatted(id))
        );
        return contactMapper.toViewDTO(existingContact);
    }

    @Transactional
    public ContactViewDTO updateContact(Long id, ContactUpdateDTO updateDTO){
        Contact existingContact = contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException("Contact with id %d does not exist".formatted(id))
        );
        contactMapper.updateContact(updateDTO, existingContact);
        return contactMapper.toViewDTO(contactRepository.save(existingContact));
    }

    public void deleteContact(Long id){
        Contact existingContact = contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException("Contact with id %d does not exist".formatted(id))
        );
        contactRepository.deleteById(id);
    }
}
