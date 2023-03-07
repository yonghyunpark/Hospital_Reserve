package hospital.reservation.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class HospitalForm {

    @NotEmpty(message = "이름 입력은 필수 입니다")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
