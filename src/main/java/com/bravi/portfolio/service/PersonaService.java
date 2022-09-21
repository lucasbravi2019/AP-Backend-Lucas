package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.PersonaRequest;
import com.bravi.portfolio.dto.PersonaResponse;

public interface PersonaService {

    PersonaResponse getPersona();
    PersonaResponse editPersona(PersonaRequest personaRequest);

}
