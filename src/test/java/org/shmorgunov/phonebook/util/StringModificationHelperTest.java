package org.shmorgunov.phonebook.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.shmorgunov.phonebook.util.StringModificationHelper.formatPhoneNumber;
import static org.shmorgunov.phonebook.util.StringModificationHelper.trimName;

class StringModificationHelperTest {


    static final String NULL_NAME = null;
    static final String VALID_NAME = "Bob";
    static final String EMPTY_NAME = "";
    static final String UNTRIMMED_NAME = "          Bob        ";
    static final String INVALID_LARGE_NAME = "sdfghasadafasdhaskjhdjhasdhaskjhdakjsdbnasjdkhbajkshdjkashdjashdjkahsdkjhasjkdhasjkdhajksdkjashdjkashdjkashdjkashdjkahsdjkashdjkashdjkashdjkashdzxnmcbabhsjkhuiqwdhasjdbnzjkxbcjkhaskjhdwqiunbdjkashcjkhasduiwbndkjasbchuaihwuihjkashfdhauidhasdkznbxcjkbasdhuoiwhucdbasujcjkzhsc";
    static final String NULL_PHONE = null;
    static final String INVALID_SHORT_PHONE = "22";
    static final String INVALID_LONG_PHONE = "791120013549";
    static final String VALID_PHONE_STARTS_WITH_8 = "89112001354";
    static final String DATABASE_PHONE_FORMAT = "79112001354";
    static final String VALID_PHONE_STARTS_WITH_PLUS = "+79112001354";
    static final String INVALID_PHONE_STARTS_WITH_PLUS_AND_EIGHT = "+89112001354";


    @Test
    void removesLeadinPlusFromPhone() {
        assertEquals(DATABASE_PHONE_FORMAT, formatPhoneNumber(VALID_PHONE_STARTS_WITH_PLUS));
    }

    @Test
    void returnsValidPhoneFromValid() {
        assertEquals(DATABASE_PHONE_FORMAT, formatPhoneNumber(DATABASE_PHONE_FORMAT));
    }

    @Test
    void changesLeadingEightToSevenInPhone() {
        assertEquals(DATABASE_PHONE_FORMAT, formatPhoneNumber(VALID_PHONE_STARTS_WITH_8));
    }

    @Test
    void notChangesLeadingPlusAndEightToValidFormat() {
        assertEquals(INVALID_PHONE_STARTS_WITH_PLUS_AND_EIGHT, formatPhoneNumber(INVALID_PHONE_STARTS_WITH_PLUS_AND_EIGHT));
    }

    @Test
    void returnsInitialInvalidLongPhone() {
        assertEquals(INVALID_LONG_PHONE, formatPhoneNumber(INVALID_LONG_PHONE));
    }

    @Test
    void returnsInitialInvalidShortPhone() {
        assertEquals(INVALID_SHORT_PHONE, formatPhoneNumber(INVALID_SHORT_PHONE));
    }

    @Test
    void returnsInitialNullPhone() {
        assertEquals(NULL_PHONE, formatPhoneNumber(NULL_PHONE));
    }


    @Test
    void returnsInitialNullName() {
        assertEquals(NULL_NAME, trimName(NULL_NAME));
    }

    @Test
    void returnsInitialEmptyName() {
        assertEquals(EMPTY_NAME, trimName(EMPTY_NAME));
    }

    @Test
    void formatNameWithSpacesToValidName() {
        assertEquals(VALID_NAME, trimName(UNTRIMMED_NAME));
    }

    @Test
    void returnsInitialLongName() {
        assertEquals(INVALID_LARGE_NAME, trimName(INVALID_LARGE_NAME));
    }

}