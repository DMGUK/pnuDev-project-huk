package com.dmytrohuk.weborganizer;

import com.dmytrohuk.weborganizer.contacts.Contact;
import com.dmytrohuk.weborganizer.contacts.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ContactRepositoryTest {

    /*
    * TODO:
    *  Delete repository tests
    * */

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindById() {
        Contact contact = new Contact();
        contact.setId(1L);
        contact.setName("testName");
        when(contactRepository.findById(1L)).thenReturn(Optional.of(contact));
        Optional<Contact> result = contactRepository.findById(1L);
        assertEquals(Optional.of(contact), result);
    }

    @Test
    public void userRepositoryUsernames(){
        String name = "testName";
        String surname = "testSurname";
        Contact testContact = new Contact();
        testContact.setId(4L);
        testContact.setName(name);
        testContact.setSurname(surname);
        when(contactRepository.findByName(name)).thenReturn(testContact);
        Contact resultContact = contactRepository.findByName(name);
        assertEquals(testContact, resultContact);
    }
}
