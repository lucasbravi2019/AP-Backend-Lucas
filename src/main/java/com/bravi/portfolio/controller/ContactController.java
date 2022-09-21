package com.bravi.portfolio.controller;

import com.bravi.portfolio.dto.ContactRequest;
import com.bravi.portfolio.dto.ContactResponse;
import com.bravi.portfolio.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {

    private final ContactService contactService;


    @PostMapping
    public ResponseEntity<ContactResponse> createContact(@Valid @RequestBody ContactRequest contactRequest) {
        return ResponseEntity.ok(contactService.createContact(contactRequest));
    }

    @PutMapping
    public ResponseEntity<ContactResponse> editContact(@Valid @RequestBody ContactRequest contactRequest) {
        return ResponseEntity.ok(contactService.editContact(contactRequest));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteContact(@RequestBody Long id) {
        contactService.deleteContact(id);
        return ResponseEntity.ok().build();
    }

}
