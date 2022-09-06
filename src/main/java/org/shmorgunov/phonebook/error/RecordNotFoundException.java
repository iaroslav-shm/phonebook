package org.shmorgunov.phonebook.error;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException(Long id) {
        super("Sample id not found :" + id);
    }
}
