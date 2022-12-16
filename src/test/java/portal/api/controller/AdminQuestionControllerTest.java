package portal.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import portal.api.dto.AnswerDto;
import portal.api.dto.QuestionRequestDto;

import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class AdminQuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateNotValidAnswerCountCorrectly() throws Exception {
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("dsddsd", 1L,
                Set.of(
                        new AnswerDto("1", false),
                        new AnswerDto("2", false),
                        new AnswerDto("3", false),
                        new AnswerDto("4", false)));

        String body = objectMapper.writeValueAsString(questionRequestDto);
        mockMvc.perform(post("/api/admin/quizzes/questions")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateNotValidAnswerCountAll() throws Exception {
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("dsddsd", 1L,
                Set.of(
                        new AnswerDto("1", false),
                        new AnswerDto("4", false)));

        String body = objectMapper.writeValueAsString(questionRequestDto);
        mockMvc.perform(post("/api/admin/quizzes/questions")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateNotValidAnswerReplyIsBlank() throws Exception {
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("dsddsd", 1L,
                Set.of(
                        new AnswerDto("  ", true),
                        new AnswerDto("   ", false),
                        new AnswerDto("3", false),
                        new AnswerDto("4", false)));

        String body = objectMapper.writeValueAsString(questionRequestDto);
        mockMvc.perform(post("/api/admin/quizzes/questions")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreateNotValidAnswerCollectionIsEmpty() throws Exception {
        QuestionRequestDto questionRequestDto = new QuestionRequestDto("dsddsd", 1L,
                Set.of());

        String body = objectMapper.writeValueAsString(questionRequestDto);
        mockMvc.perform(post("/api/admin/quizzes/questions")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}
