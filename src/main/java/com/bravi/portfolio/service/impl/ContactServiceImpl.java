package com.bravi.portfolio.service.impl;

import com.bravi.portfolio.dto.ContactRequest;
import com.bravi.portfolio.dto.ContactResponse;
import com.bravi.portfolio.mapper.ContactMapper;
import com.bravi.portfolio.repository.ContactRepository;
import com.bravi.portfolio.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactMapper contactMapper;
    private final ContactRepository contactRepository;

    @Override
    public ContactResponse createContact(ContactRequest contactRequest) {
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(contactRequest)));
    }

    @Override
    public ContactResponse editContact(ContactRequest contactRequest) {
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(contactRequest)));
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

}
