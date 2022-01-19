package io.turntabl.leaderboardservice.controller;

import io.swagger.v3.oas.annotations.Operation;

import io.turntabl.leaderboardservice.controller.response.LanguageProfileDto;

import io.turntabl.leaderboardservice.client.request.AddUserDto;

import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import io.turntabl.leaderboardservice.enums.Languages;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/leaderboard")
public class LeaderboardController {

    private final LeaderboardFacade leaderboardFacade;

    @GetMapping
    @Operation(summary = "Fetch current leaderboard")
    public List<ProfileDto> getLeaderboard() {
        return leaderboardFacade.getLeaderboard();
    }

    @GetMapping("/{language}")
    @Operation(summary = "Get leaderboard by language")
    public List<LanguageProfileDto> getLeaderboardByLanguage(@PathVariable Languages language) {
        return leaderboardFacade.getProfileByLanguage(language.name());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new user.")
    public boolean addUser(@RequestBody AddUserDto addUserDto) {
        return leaderboardFacade.addUser(addUserDto);
    }



}
