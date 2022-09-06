package org.shmorgunov.phonebook.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.shmorgunov.phonebook.service.PhoneBookService;
import org.shmorgunov.phonebook.util.ApiConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiConstants.RECORDS,
        produces = "application/json")
@CrossOrigin(origins = "http://phonebook:8080")
public class PhoneBookController {

    private PhoneBookService phoneBookService;

    @Autowired
    public PhoneBookController(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @GetMapping()
    @ResponseBody
    public List<PhoneRecord> phoneRecordList() {
        return phoneBookService.listAllPhoneRecords();
    }

    @GetMapping(ApiConstants.RECORD_ID)
    public ResponseEntity<PhoneRecord> phoneRecordById(@PathVariable("id") Long id) {
        Optional<PhoneRecord> optionalPhoneRecord = phoneBookService.findPhoneRecordById(id);
        return optionalPhoneRecord
                .map(phoneRecord -> new ResponseEntity<>(phoneRecord, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PhoneRecord postPhoneRecord(@RequestBody PhoneRecord phoneRecord) {
        return phoneBookService.saveRecord(phoneRecord);
    }

    @PatchMapping(path = ApiConstants.RECORD_ID, consumes = "application/json")
    public ResponseEntity<PhoneRecord> patchPhoneRecord(@PathVariable("id") Long id, @RequestBody PhoneRecord patch) {
        Optional<PhoneRecord> optionalPhoneRecord = phoneBookService.patchPhoneRecordById(id, patch);
        return optionalPhoneRecord
                .map(phoneRecord -> new ResponseEntity<>(phoneRecord, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(ApiConstants.RECORD_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long orderId) {
        try {
            phoneBookService.deletePhoneRecordById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
