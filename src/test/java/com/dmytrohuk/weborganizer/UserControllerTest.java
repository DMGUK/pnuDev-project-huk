package com.dmytrohuk.weborganizer;
import com.dmytrohuk.weborganizer.users.User;
import com.dmytrohuk.weborganizer.users.UserRepository;
import com.dmytrohuk.weborganizer.users.UserService;
import org.aspectj.weaver.patterns.IToken;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.CannotReadScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.hamcrest.collection.IsMapWithSize.aMapWithSize;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class UserControllerTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @BeforeTestClass
    private void executeSqlScript() throws Exception{
        final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        final String JDBC_USERNAME = "sa";
        final String JDBC_PASSWORD = "";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)) {
            try {
//                String scriptPath1 = "src/test/java/resources/init/create-table.sql";
//                Resource scriptResource1 = new ClassPathResource(scriptPath1);
//                ScriptUtils.executeSqlScript(connection, scriptResource1);
                String scriptPath2 = "src/test/java/resources/empty/reset.sql";
                Resource scriptResource2 = new ClassPathResource(scriptPath2);
                ScriptUtils.executeSqlScript(connection, scriptResource2);
                String scriptPath3 = "src/test/java/resources/init/user-data.sql";
                Resource scriptResource3 = new ClassPathResource(scriptPath3);
                ScriptUtils.executeSqlScript(connection, scriptResource3);
            } catch (Exception e) {
                new IllegalStateException("The file does not exist");
            }
        }
    }

    @Test
    @WithMockUser(username = "username")
    void testCreateUser() throws Exception{
        final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        final String JDBC_USERNAME = "username";
        final String JDBC_PASSWORD = "password";

        final File jsonFile1 = new FileSystemResource("src/test/java/resources/init/user.json").getFile();
        final String userToCreate = Files.readString(jsonFile1.toPath());

        this.mockMvc.perform(
            post("/api/users")
            .contentType(APPLICATION_JSON)
            .content(userToCreate)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isMap());

        assertNotEquals(this.userRepository.findByUsername("username6").getId(), 6);
    }
    @Test
    @WithMockUser(username = "username")
    void testGetUserById() throws Exception{
        final File jsonFile1 = new FileSystemResource("src/test/java/resources/init/user.json").getFile();
        final String userToCreate = Files.readString(jsonFile1.toPath());

        this.mockMvc.perform(get("/api/users/{id}", 6))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(6));
    }
}
