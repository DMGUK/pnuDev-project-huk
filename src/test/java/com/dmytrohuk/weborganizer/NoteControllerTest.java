package com.dmytrohuk.weborganizer;

import com.dmytrohuk.weborganizer.notes.NoteRepository;
import com.dmytrohuk.weborganizer.users.UserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.test.context.support.WithMockUser;
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
        @Sql(value = "classpath:users/empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:users/init/create-table.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:users/init/user-data.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:notes/empty/reset.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:notes/init/create-table.sql", executionPhase = BEFORE_TEST_METHOD),
        @Sql(value = "classpath:notes/init/user-data.sql", executionPhase = BEFORE_TEST_METHOD)
})
public class NoteControllerTest {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "username")
    void testCreateNote() throws Exception{
        final File jsonFile = new FileSystemResource("src/test/resources/notes/init/note.json").getFile();
        final String noteToCreate = Files.readString(jsonFile.toPath());

        this.mockMvc.perform(
                        post("/api/notes")
                                .contentType(APPLICATION_JSON)
                                .content(noteToCreate)
                )
                .andDo(print())
                .andExpect(status().isOk());

        assertNotEquals(this.noteRepository.findByTitle("title six").getId(), 6);
    }
    @Test
    @WithMockUser(username = "username")
    void testGetNoteById() throws Exception{
        this.mockMvc.perform(get("/api/notes/{id}", 5))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(this.noteRepository.findByTitle("title five").getId(), 5);
    }

    @Test
    @WithMockUser(username = "username")
    void testUpdateNoteById() throws Exception{
        final File jsonFile = new FileSystemResource("src/test/resources/notes/init/update.json").getFile();
        final String userToUpdate = Files.readString(jsonFile.toPath());
        this.mockMvc.perform(put("/api/notes/{id}", 5)
                        .contentType(APPLICATION_JSON)
                        .content(userToUpdate))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(this.noteRepository.findById(5l).get().getTitle(), "new title");
    }

    @Test
    @WithMockUser(username = "username")
    void testDeleteNoteById() throws Exception {
        this.mockMvc.perform(delete("/api/notes/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk());

        assertEquals(this.noteRepository.findAll().size(), 4);
    }
}
