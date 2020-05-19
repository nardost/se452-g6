package edu.depaul.g6;

import edu.depaul.g6.facilities.service.Facilities;
import edu.depaul.g6.facilities.repository.LocationRepository;

import org.junit.Ignore;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@DataJpaTest
@EnableJpaRepositories(basePackages = { "edu.depaul.g6" })
@EntityScan(basePackages = { "edu.depaul.g6" })
public class DemoTest {

    @Bean
    private Facilities injectFacilities() {
        return new Facilities();
    }

    @Autowired
    private Facilities facilities;

    @Autowired
    private LocationRepository repository;

    @Ignore
    @Test
    public void demoTest() {
        assertThat(repository).isNotNull();
    }

    @Ignore
    @Test
    public void facilitiesTest() {
        assertThat(facilities).isNotNull();
    }
}
