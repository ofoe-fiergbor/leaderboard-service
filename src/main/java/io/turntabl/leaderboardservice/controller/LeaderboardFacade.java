package io.turntabl.leaderboardservice.controller;

import io.turntabl.leaderboardservice.controller.response.LanguageProfileDto;
import io.turntabl.leaderboardservice.client.request.AddUserDto;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.converter.ProfileToProfileDtoConverter;
import io.turntabl.leaderboardservice.model.Profile;
import io.turntabl.leaderboardservice.service.LeaderboardRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Log4j2
@RequiredArgsConstructor
@Component
public class LeaderboardFacade {

    private final LeaderboardRepositoryService leaderboardRepositoryService;
    private final ProfileToProfileDtoConverter profileToProfileDtoConverter;

    public List<ProfileDto> getLeaderboard() {
        return leaderboardRepositoryService.getProfiles().stream()
                .map(profileToProfileDtoConverter::convert)
                .collect(toList());
    }

    public List<LanguageProfileDto> getProfileByLanguage(String language) {
        return leaderboardRepositoryService.getProfileByLanguage(language);
    }
    public boolean addUser(AddUserDto addUserDto) {
        return leaderboardRepositoryService.addUser(addUserDto);
    }

}
