package io.turntabl.leaderboardservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.turntabl.leaderboardservice.controller.response.ProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
