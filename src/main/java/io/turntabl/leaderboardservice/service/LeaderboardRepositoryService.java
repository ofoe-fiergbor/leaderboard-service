package io.turntabl.leaderboardservice.service;

import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LeaderboardRepositoryService {

    private final ProfileRepository profileRepository;

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    public List<Profile> getProfileByLanguage(String language) {
        List<Profile> result = new ArrayList<>();
        profileRepository.findAll().forEach(profile -> {
            profile.getLanguageLevels().stream()
                    .filter(languageLevel -> languageLevel.getName().toLowerCase()
                            .equals(language.toLowerCase())).map(languageLevel -> profile)
                    .forEach(result::add);
        });
        return result;
    }
}
