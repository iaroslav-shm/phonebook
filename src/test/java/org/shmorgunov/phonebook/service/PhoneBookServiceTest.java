package org.shmorgunov.phonebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.shmorgunov.phonebook.data.PhoneBookRepository;
import org.shmorgunov.phonebook.domain.PhoneRecord;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PhoneBookServiceTest {

    @Mock
    private PhoneBookRepository testRepository;
    private PhoneBookService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PhoneBookService(testRepository);
    }

    @Test
    void canGetAllPhoneRecords() {
        // when
        underTest.listAllPhoneRecords();
        // then
        verify(testRepository).findAll();
    }

    @Test
    void canAddPhoneRecord() {
        // given
        PhoneRecord phoneRecord = new PhoneRecord("Bob Marley", "123");

        // when
        underTest.addRecord(phoneRecord);

        // then
        ArgumentCaptor<PhoneRecord> recordArgumentCaptor =
                ArgumentCaptor.forClass(PhoneRecord.class);

        verify(testRepository).save(recordArgumentCaptor.capture());

        PhoneRecord capturedPhoneRecord = recordArgumentCaptor.getValue();

        assertThat(capturedPhoneRecord).isEqualTo(phoneRecord);
    }
}