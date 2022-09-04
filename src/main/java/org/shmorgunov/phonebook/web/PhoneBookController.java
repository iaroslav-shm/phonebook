package org.shmorgunov.phonebook.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.shmorgunov.phonebook.service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PhoneBookController {

    private PhoneBookService phoneBookService;

    @Autowired
    public PhoneBookController(PhoneBookService phoneBookService) {
        this.phoneBookService = phoneBookService;
    }

    @GetMapping("/records")
    @ResponseBody
    public List<PhoneRecord> phoneRecordList() {
        return phoneBookService.listAllPhoneRecords();
    }
}
