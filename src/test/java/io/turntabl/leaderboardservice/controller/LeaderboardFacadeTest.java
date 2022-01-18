package io.turntabl.leaderboardservice.controller;


import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LeaderboardFacade.class})
@ExtendWith(SpringExtension.class)
class LeaderboardFacadeTest {

    @Autowired
    private LeaderboardFacade underTest;

    @MockBean
    private LeaderboardRepositoryService leaderboardRepositoryService;
    @MockBean
    ProfileToProfileDtoConverter profileToProfileDtoConverter;

    @BeforeEach
    void setUp() {
        underTest = new LeaderboardFacade(leaderboardRepositoryService, profileToProfileDtoConverter);
    }
    @Test
    void getLeaderboard() {
        when(this.leaderboardRepositoryService.getProfiles()).thenReturn(new ArrayList<>());
        assertTrue(this.underTest.getLeaderboard().isEmpty());
        verify(this.leaderboardRepositoryService).getProfiles();
    }
}
