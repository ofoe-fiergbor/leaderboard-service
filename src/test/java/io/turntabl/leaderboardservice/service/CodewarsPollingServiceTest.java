package io.turntabl.leaderboardservice.service;


import io.turntabl.leaderboardservice.client.CodewarsClient;
import io.turntabl.leaderboardservice.converter.UserDtoToProfileConverter;
import io.turntabl.leaderboardservice.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CodewarsPollingService.class})
@ExtendWith(SpringExtension.class)
class CodewarsPollingServiceTest {
    @MockBean
    private CodewarsClient codewarsClient;

    @Autowired
    private CodewarsPollingService codewarsPollingService;

    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private UserDtoToProfileConverter userDtoToProfileConverter;

    @Test
    void testUpdateProfiles() {

        when(this.profileRepository.findAll()).thenReturn(new ArrayList<>());
        this.codewarsPollingService.updateProfiles();
    }
}
