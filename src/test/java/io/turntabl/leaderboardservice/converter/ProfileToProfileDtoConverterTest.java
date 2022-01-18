package io.turntabl.leaderboardservice.converter;

import io.turntabl.leaderboardservice.controller.response.LanguageLevelDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.model.LanguageLevel;
import io.turntabl.leaderboardservice.model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {ProfileToProfileDtoConverter.class})
@ExtendWith(MockitoExtension.class)
class ProfileToProfileDtoConverterTest {

    @Autowired
    private ProfileToProfileDtoConverter profileToProfileDtoConverter;


    @BeforeEach
    void setUp() {
        profileToProfileDtoConverter = new ProfileToProfileDtoConverter();
    }

    @Test
    void shouldCovertProfileToProfileDto() {
        Profile profile = new Profile();
        profile.setClan("Clan");
        profile.setHonour(1);
        profile.setId("42");
        ArrayList<LanguageLevel> languageLevelList = new ArrayList<>();
        profile.setLanguageLevels(languageLevelList);
        profile.setName("Name");
        profile.setOverallRank(1);
        ProfileDto actualConvertResult = this.profileToProfileDtoConverter.convert(profile);
        assertEquals("Clan", actualConvertResult.getClan());
        assertEquals("42", actualConvertResult.getUsername());
        assertEquals(1, actualConvertResult.getOverallRank().intValue());
        assertEquals("Name", actualConvertResult.getName());
        assertEquals(languageLevelList, actualConvertResult.getLanguages());
        assertEquals(1, actualConvertResult.getHonour().intValue());
    }




    @Test
    void convertLanguages() {
        assertTrue(this.profileToProfileDtoConverter.convertLanguages(new ArrayList<>()).isEmpty());
    }

    @Test
    void convertLanguages2() {
        Profile profile = new Profile();
        profile.setClan("Clan");
        profile.setHonour(0);
        profile.setId("42");
        profile.setLanguageLevels(new ArrayList<>());
        profile.setName("Name");
        profile.setOverallRank(0);

        LanguageLevel languageLevel = new LanguageLevel();
        languageLevel.setName("Name");
        languageLevel.setProfile(profile);
        languageLevel.setRank(0);

        ArrayList<LanguageLevel> languageLevelList = new ArrayList<>();
        languageLevelList.add(languageLevel);
        List<LanguageLevelDto> actualConvertLanguagesResult = this.profileToProfileDtoConverter
                .convertLanguages(languageLevelList);
        assertEquals(1, actualConvertLanguagesResult.size());
        LanguageLevelDto getResult = actualConvertLanguagesResult.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals(0, getResult.getRank().intValue());
    }






}
