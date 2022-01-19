package io.turntabl.leaderboardservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.enums.Languages;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {LeaderboardController.class})
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LeaderboardController.class)
class LeaderboardControllerTest {

    @Autowired
    private LeaderboardController leaderboardController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LeaderboardFacade leaderboardFacade;

    @Test
    void shouldGetLeaderboard() throws Exception {
        ProfileDto profileDto = ProfileDto.builder()
                .username("lameiraatt")
                .name("Ana Lameira")
                .build();
        List<ProfileDto> expectedResponse = List.of(profileDto);

        when(leaderboardFacade.getLeaderboard()).thenReturn(expectedResponse);

        mockMvc.perform(get("/v1/leaderboard"))
                .andExpect(status().isOk())
                .andExpect(result -> assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expectedResponse)));
    }

    @Test
    void testGetLeaderboardByLanguage() throws Exception {
        when(this.leaderboardFacade.getProfileByLanguage((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/leaderboard/{language}",
                Languages.java);
        MockMvcBuilders.standaloneSetup(this.leaderboardController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
