package hospital.reservation.controller;

import hospital.reservation.domain.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class PatientForm {

    @NotEmpty(message = "이름 입력은 필수 입니다.")
    private String name;

    private Long age;

    //@Enumerated(EnumType.STRING) 안써도되나봄
    private Gender gender;
}
