package io.turntabl.leaderboardservice.service;

import io.turntabl.leaderboardservice.controller.response.LanguageProfileDto;
import io.turntabl.leaderboardservice.model.LanguageLevel;
import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.repository.ProfileRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LeaderboardRepositoryService.class})
@ExtendWith(MockitoExtension.class)
class LeaderboardRepositoryServiceTest {


    @Mock
    private ProfileRepository profileRepository;
    @Autowired
    private LeaderboardRepositoryService underTest;

    @BeforeEach
    void setUp() {
        underTest = new LeaderboardRepositoryService(profileRepository);
    }

    @Test
    void shouldGetProfiles() {
        // given
        Profile profile1 = mock(Profile.class);
        Profile profile2 = mock(Profile.class);

        when(profileRepository.findAll()).thenReturn(List.of(profile1, profile2));

        // when
        List<Profile> result = underTest.getProfiles();

        // then
        assertThat(result).containsExactly(profile1, profile2);
    }

    @Test
    void testGetProfileByLanguage() {
        when(this.profileRepository.findAll()).thenReturn(new ArrayList<>());
        List<LanguageProfileDto> actualProfileByLanguage = underTest.getProfileByLanguage("en");
        assertTrue(actualProfileByLanguage.isEmpty());
        verify(this.profileRepository).findAll();
        assertEquals(actualProfileByLanguage, underTest.getProfiles());
    }

    @Test
    void testGetProfileByLanguage2() {
        Profile profile = new Profile();
        profile.setClan("Clan");
        profile.setHonour(0);
        profile.setId("42");
        profile.setLanguageLevels(new ArrayList<>());
        profile.setName("Name");
        profile.setOverallRank(0);

        ArrayList<Profile> profileList = new ArrayList<>();
        profileList.add(profile);
        when(profileRepository.findAll()).thenReturn(profileList);
        assertTrue(underTest.getProfileByLanguage("en").isEmpty());
        verify(profileRepository).findAll();
        assertEquals(1, underTest.getProfiles().size());
    }



}
