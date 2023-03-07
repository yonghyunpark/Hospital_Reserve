package hospital.reservation.controller;

import hospital.reservation.domain.Hospital;
import hospital.reservation.domain.MedicalDepartment;
import hospital.reservation.service.HospitalService;
import hospital.reservation.service.MedicalDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MedicalDepartmentController {

    private final MedicalDepartmentService medicalDepartmentService;
    private final HospitalService hospitalService;


    @GetMapping("/medicalDepartments/new")
    public String createForm(Model model){
        List<Hospital> hospitals = hospitalService.findHospitals();

        model.addAttribute("hospitals", hospitals);
        model.addAttribute("medicalDepartmentForm", new MedicalDepartmentForm());
        return "medicalDepartments/createMedicalDepartmentForm";
    }


    @PostMapping("/medicalDepartments/new")
    public String create(@Valid MedicalDepartmentForm form, BindingResult result,
                         @RequestParam("hospitalId") Long hospitalId){

        if(result.hasErrors()){
            return "medicalDepartments/createMedicalDepartmentForm";
        }

        MedicalDepartment medicalDepartment = new MedicalDepartment();
        medicalDepartment.setName(form.getName());
        medicalDepartment.setPhoneNum(form.getPhoneNum());
        medicalDepartment.setHospital(hospitalService.findOne(hospitalId));

        medicalDepartmentService.register(medicalDepartment, hospitalId);
        return "redirect:/";
    }

    @GetMapping("/medicalDepartments")
    public String list(Model model){
        List<MedicalDepartment> medicalDepartments = medicalDepartmentService.findMedicalDepartments();
        model.addAttribute("medicalDepartments",medicalDepartments);
        return "medicalDepartments/medicalDepartmentList";
    }
}
