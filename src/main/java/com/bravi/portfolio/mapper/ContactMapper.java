package com.bravi.portfolio.mapper;

import com.bravi.portfolio.dto.ContactRequest;
import com.bravi.portfolio.dto.ContactResponse;
import com.bravi.portfolio.entity.Contact;
import com.bravi.portfolio.repository.ContactRepository;
import com.bravi.portfolio.repository.PersonaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ContactMapper {

    private PersonaRepository personaRepository;
    private ContactRepository contactRepository;

    public Contact toEntity(ContactRequest contactRequest) {
        Contact contact = Contact.builder().build();
        if (contactRequest.getId() != null) {
            contact = contactRepository.findById(contact.getId())
                    .orElseThrow();
        }

        contact.setContactValue(contactRequest.getContactValue());
        contact.setContactType(contactRequest.getContactType());
        if (contactRequest.getPersonaId() != null) {
            contact.setPersona(personaRepository.findById(contactRequest.getPersonaId())
                    .orElseThrow());
        }

        return contact;
    }

    public ContactResponse toDto(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .contactType(contact.getContactType())
                .contactValue(contact.getContactValue())
                .build();
    }
}
