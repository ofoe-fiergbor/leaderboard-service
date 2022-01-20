package io.turntabl.leaderboardservice.service;

import io.turntabl.leaderboardservice.controller.response.LanguageProfileDto;
import io.turntabl.leaderboardservice.client.request.AddUserDto;
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

    public List<LanguageProfileDto> getProfileByLanguage(String language) {
        List<LanguageProfileDto> languageProfileDtos = new ArrayList<>();

        getProfiles().forEach(p -> p.getLanguageLevels().stream().filter(l -> l.getName().equals(language)).map(l -> LanguageProfileDto.builder()
                .languageRank(l.getRank())
                .username(p.getId())
                .clan(p.getClan())
                .honour(p.getHonour())
                .overallRank(p.getOverallRank())
                .build())
                .forEach(languageProfileDtos::add));

        return languageProfileDtos;
    }

    public boolean addUser(AddUserDto addUserDto) {
        if (profileRepository.existsById(addUserDto.getUsername())) {
            return false;
        }
        profileRepository.addNewUser(addUserDto.getUsername());
        return  true;
    }

}
