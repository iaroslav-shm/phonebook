package org.shmorgunov.phonebook.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstants {
    public static final String RECORDS = "/records";
    public static final String RECORD_ID = "/{id}";
}
