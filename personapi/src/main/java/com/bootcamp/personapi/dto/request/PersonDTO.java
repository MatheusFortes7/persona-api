package com.bootcamp.personapi.dto.request;

import com.bootcamp.personapi.entity.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    private String firstname;

    private String lastname;

    private String cpf;

    private LocalDate birthDate;

    private List<Phone> phones;
}
