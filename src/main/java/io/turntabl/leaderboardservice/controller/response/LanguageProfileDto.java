package io.turntabl.leaderboardservice.controller.response;

import lombok.*;

@Value
@Builder
public class LanguageProfileDto {

    String username;

    String clan;

    Integer honour;

    Integer languageRank;

    Integer overallRank;

}
