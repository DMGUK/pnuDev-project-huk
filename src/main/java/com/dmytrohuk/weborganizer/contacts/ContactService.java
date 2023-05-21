package com.dmytrohuk.weborganizer.contacts;

import com.dmytrohuk.weborganizer.security.AuthUser;
import com.dmytrohuk.weborganizer.security.AuthUserService;
import com.dmytrohuk.weborganizer.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    private final ContactMapper contactMapper;

    private final UserRepository userRepository;

    private final AuthUserService authUserService;

    public ContactViewDTO createContact(ContactCreateDTO createDTO, AuthUser authUser){
        Contact contact = contactMapper.toContact(createDTO);
        UserDetails user = authUserService.loadUserByUsername(authUser.getUsername());
        Long userId = userRepository.findByUsername(user.getUsername()).getId();
        contact.setUserId(userId);
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
