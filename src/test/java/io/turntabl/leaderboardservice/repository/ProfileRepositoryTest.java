package io.turntabl.leaderboardservice.repository;

import io.turntabl.leaderboardservice.model.Profile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProfileRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProfileRepository underTest;

    @Test
    public void shouldFindExistingProfiles() {
        List<Profile> profiles = underTest.findAll();
        assertThat(profiles).isNotEmpty();
    }

    @Test
    public void saveProfileTest() {
        Profile  mock = new Profile();
        mock.setId("dummy");
        mock.setName("ofoe");
        mock.setHonour(77);
        Profile profile = underTest.save(mock);
        assertThat(profile.getId()).isEqualTo("dummy");
    }


}
