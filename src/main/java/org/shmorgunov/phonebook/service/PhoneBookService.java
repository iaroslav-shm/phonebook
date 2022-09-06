package org.shmorgunov.phonebook.service;

import lombok.AllArgsConstructor;
import org.shmorgunov.phonebook.data.PhoneBookRepository;
import org.shmorgunov.phonebook.domain.PhoneRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PhoneBookService {

    private PhoneBookRepository bookRepository;

    public List<PhoneRecord> listAllPhoneRecords() {
        return bookRepository.findAll();
    }

    public PhoneRecord saveRecord(PhoneRecord phoneRecord) {
        String trimmedName = phoneRecord.getName().trim();
        phoneRecord.setName(trimmedName);
        String trimmedPhone = phoneRecord.getPhone().trim();
        phoneRecord.setName(trimmedPhone);
        return bookRepository.save(phoneRecord);
    }

    public Optional<PhoneRecord> findPhoneRecordById(Long id) {
        return bookRepository.findById(id);
    }

    public void deletePhoneRecordById(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<PhoneRecord> patchPhoneRecordById(Long id, PhoneRecord patch) {
        Optional<PhoneRecord> phoneRecordOptional = bookRepository.findById(id);

        if (phoneRecordOptional.isPresent()) {
            PhoneRecord phoneRecord = phoneRecordOptional.get();
            if (patch.getPhone() != null) {
                String phone = patch.getPhone().trim();
                phoneRecord.setPhone(phone);
                phoneRecord.setLastModified(LocalDateTime.now());
            }
            if (patch.getName() != null) {
                String name = patch.getName().trim();
                phoneRecord.setName(name);
                phoneRecord.setLastModified(LocalDateTime.now());
            }
            bookRepository.save(phoneRecord);
        }
        return phoneRecordOptional;
    }
}
