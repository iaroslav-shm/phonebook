package org.shmorgunov.phonebook.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Defines constants for our API endpoints
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiConstants {
    public static final String RECORDS = "/records";
    public static final String RECORD_ID = "/{id}";
}
