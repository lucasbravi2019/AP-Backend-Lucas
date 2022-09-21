package com.bravi.portfolio.service;

import com.bravi.portfolio.dto.ContactRequest;
import com.bravi.portfolio.dto.ContactResponse;

public interface ContactService {

    ContactResponse createContact(ContactRequest contactRequest);
    ContactResponse editContact(ContactRequest contactRequest);
    void deleteContact(Long id);

}
