package io.turntabl.leaderboardservice.service;

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
    public boolean addUser(AddUserDto addUserDto) {
        profileRepository.addNewUser(addUserDto.getUsername());
        return  true;
    }

}
