package io.turntabl.leaderboardservice.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = {UserDtoToProfileConverter.class})
@ExtendWith(SpringExtension.class)
class UserDtoToProfileConverterTest {

    @Autowired
    private UserDtoToProfileConverter userDtoToProfileConverter;


    @BeforeEach
    void setUp() {
        userDtoToProfileConverter = new UserDtoToProfileConverter();
    }

    @Test
    void convertLanguagesTest() {
        assertTrue(this.userDtoToProfileConverter.convertLanguages(new HashMap<>()).isEmpty());
    }

    @Test
    void shouldConvertUserDtoToProfile() {

    }
}
