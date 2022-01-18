package io.turntabl.leaderboardservice.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void testSetLanguageLevels() {
        Profile profile = new Profile();
        Profile actualSetLanguageLevelsResult = profile.setLanguageLevels(new ArrayList<>());
        assertSame(profile, actualSetLanguageLevelsResult);
        assertTrue(actualSetLanguageLevelsResult.getLanguageLevels().isEmpty());
    }

    @Test
    void testSetLanguageLevels2() {
        Profile profile = new Profile();

        Profile profile1 = new Profile();
        profile1.setClan("Clan");
        profile1.setHonour(0);
        profile1.setId("42");
        profile1.setLanguageLevels(new ArrayList<>());
        profile1.setName("Name");
        profile1.setOverallRank(0);

        LanguageLevel languageLevel = new LanguageLevel();
        languageLevel.setName("Name");
        languageLevel.setProfile(profile1);
        languageLevel.setRank(0);

        ArrayList<LanguageLevel> languageLevelList = new ArrayList<>();
        languageLevelList.add(languageLevel);
        Profile actualSetLanguageLevelsResult = profile.setLanguageLevels(languageLevelList);
        assertSame(profile, actualSetLanguageLevelsResult);
        List<LanguageLevel> languageLevels = actualSetLanguageLevelsResult.getLanguageLevels();
        assertEquals(1, languageLevels.size());
        assertSame(actualSetLanguageLevelsResult, languageLevels.get(0).getProfile());
    }

}