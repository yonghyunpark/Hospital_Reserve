package hospital.reservation.controller;

import hospital.reservation.domain.Hospital;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MedicalDepartmentForm {

    @NotEmpty(message = "이름 입력은 필수 입니다")
    private String name;

    private String phoneNum;
    private Hospital hospital;
}
