package bg.softuni.patfinder2.web.rest;

import bg.softuni.patfinder2.model.dto.CommentCreationDto;
import bg.softuni.patfinder2.model.dto.CommentMessageDto;
import bg.softuni.patfinder2.model.views.CommentDisplayView;
import bg.softuni.patfinder2.service.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.Lob;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {
    private static final Long ROUTE_ID = 1L;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService commentService;

    @Test
    public void getComments_TwoCommentsExists_commentsReturnedAsJsonAndStatusIsOk() throws Exception {
        when(this.commentService.getAllCommentsForRoute(ROUTE_ID))
                .thenReturn(List.of(
                        new CommentDisplayView(1L, "John Doe", "This is comment #1"),
                        new CommentDisplayView(2L, "Foo bar", "This is comment #2")
                ));

        this.mockMvc.perform(get("/api/" + ROUTE_ID + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].authorName", is("John Doe")))
                .andExpect(jsonPath("$.[0].message", is("This is comment #1")))
                .andExpect(jsonPath("$.[1].authorName", is("Foo bar")))
                .andExpect(jsonPath("$.[1].message", is("This is comment #2")));
    }

    @Test
    @WithMockUser(username = "testUsername")
    public void createComment_sampleData_commentIsReturnedAsExpected() throws Exception {
        when(this.commentService.createCommend(any())).thenAnswer(interaction -> {
            CommentCreationDto commentCreationDto = interaction.getArgument(0);
            return new CommentDisplayView(1L, commentCreationDto.getUsername(), commentCreationDto.getMessage());
        });
        CommentMessageDto commentMessageDto = new CommentMessageDto("This is message #1");
        ObjectMapper objectMapper = new ObjectMapper();

        this.mockMvc.perform(post("/api/" + ROUTE_ID + "/comments")
                        .content(objectMapper.writeValueAsString(commentMessageDto))
                        .with(csrf())
                        .contentType("application/json")
                        .accept("application/json"))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.message", is("This is message #1")))
                .andExpect(jsonPath("$.authorName", is("testUsername")));
    }
}
