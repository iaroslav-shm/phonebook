package org.shmorgunov.phonebook.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

import static org.shmorgunov.phonebook.util.StringModificationHelper.formatPhoneNumber;
import static org.shmorgunov.phonebook.util.StringModificationHelper.trimName;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class PhoneRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name must be not null")
    @Size(min = 1, max = 256, message = "Please provide a valid name between 1 and 256 characters")
    private String name;

    @NotNull(message = "Phone must be not null")
    @Pattern(regexp = "^(\\+7|([78]))(\\d{10})$",
            message = "Phone must be eleven digit and starts with 7 or 8, valid examples 78005551133, 88129992277, +79112003333")
    private String phone;

    @EqualsAndHashCode.Exclude
    private LocalDateTime lastModified = LocalDateTime.now();

    public PhoneRecord(String name, String phone) {
        this.name = trimName(name);
        this.phone = formatPhoneNumber(phone);
    }
}
