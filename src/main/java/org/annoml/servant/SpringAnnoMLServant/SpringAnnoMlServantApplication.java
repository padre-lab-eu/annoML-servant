package org.annoml.servant.SpringAnnoMLServant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringAnnoMlServantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAnnoMlServantApplication.class, args);
    }

}
