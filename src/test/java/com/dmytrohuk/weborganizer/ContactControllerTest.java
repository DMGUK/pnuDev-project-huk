package com.dmytrohuk.weborganizer;

import com.dmytrohuk.weborganizer.contacts.ContactRepository;
import com.dmytrohuk.weborganizer.notes.NoteRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:contacts/empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:contacts/init/create-table.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:contacts/init/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
})

public class ContactControllerTest {
    @Autowired
    private ContactRepository contactRepository;


    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("username2")
    void testCreateContact() throws Exception{
        final File jsonFile = new FileSystemResource("src/test/resources/contacts/init/contact.json").getFile();
        final String noteToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(
                        post("/api/contacts")
                                .contentType(APPLICATION_JSON)
                                .content(noteToCreate)
                )
                .andDo(print())
                .andExpect(status().isOk());

        assertNotEquals(this.contactRepository.findByName("name six").getId(), 6);
    }
    @Test
    @WithUserDetails("username2")
    void testGetNoteById() throws Exception{
        this.mockMvc.perform(get("/api/contacts/{id}", 5))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(this.contactRepository.findByName("name five").getId(), 5);
    }

    @Test
    @WithUserDetails("username2")
    void testUpdateNoteById() throws Exception{
        final File jsonFile = new FileSystemResource("src/test/resources/contacts/init/update.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());
        this.mockMvc.perform(put("/api/contacts/{id}", 5)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(this.contactRepository.findById(5l).get().getName(), "new name");
    }

    @Test
    @WithUserDetails("username2")
    void testDeleteNoteById() throws Exception {
        this.mockMvc.perform(delete("/api/contacts/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(this.contactRepository.findAll().size(), 4);
    }
}
