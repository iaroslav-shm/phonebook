package org.shmorgunov.phonebook.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Defines helper methods to format valid input data before saving to data base
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringModificationHelper {
//    /**
//     * Check if phone have a leading plus sign, and removes it before saving to Data Base
//     *
//     * @param phone string to test for plus sign
//     * @return initial phone number without plus sign
//     */
//    public static String checkAndRemovePlusSign(String phone) {
//        if (phone != null && phone.charAt(0) == '+') {
//            return phone.substring(1);
//        }
//        return phone;
//    }
//
//    /**
//     * Check if phone have a leading eight sign, and removes it before saving to Data Base
//     *
//     * @param phone string to test for eight sign
//     * @return initial phone number with seven sign
//     */
//    public static String checkAndRemoveEightSign(String phone) {
//        if (phone != null && phone.charAt(0) == '8') {
//            return "7".concat(phone.substring(1));
//        }
//        return phone;
//    }

    /**
     * Format phone number before saving to Data Base
     * Replace leading eight char and/or plus char and removes it.
     *
     * @param phone string
     * @return returns formatted phone number for Russian region 79002557897
     */
    public static String formatPhoneNumber(String phone) {
        if ((phone != null) && (phone.length() >= 11) && (phone.length() <= 12)) {
            if (phone.startsWith("8")) {
                phone = "7".concat(phone.substring(1));
            }
            if (phone.startsWith("+7")) {
                phone = phone.substring(1);
            }
            return phone;
        }
        return phone;
    }

    /**
     * Trim white spaces from name before saving to Data Base
     *
     * @param name string to test for white spaces
     * @return trimmed string or null if string was null
     */
    public static String trimName(String name) {
        if (name != null) {
            return name.trim();
        }
        return null;
    }
}
