package com.bravi.portfolio.seed;

import com.bravi.portfolio.entity.About;
import com.bravi.portfolio.entity.Persona;
import com.bravi.portfolio.repository.AboutRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PersonaSeed implements CommandLineRunner {

    private final PersonaRepository personaRepository;
    private final AboutRepository aboutRepository;

    @Value("${portfolio.persona.firstName}")
    private String firstName;

    @Value("${portfolio.persona.lastname}")
    private String lastName;

    @Value("${portfolio.about.aboutMsg}")
    private String aboutMsg;
    @Override

    public void run(String... args) throws Exception {
        if (personaRepository.count() == 0) {
            Persona persona = Persona.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .build();

            personaRepository.save(persona);
            if (aboutRepository.count() == 0) {
                About about = About.builder()
                        .aboutMsg(aboutMsg)
                        .persona(persona)
                        .build();

                aboutRepository.save(about);
            }
        }
    }
}
