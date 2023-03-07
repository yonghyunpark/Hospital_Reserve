package hospital.reservation.controller;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.MedicalDepartment;
import hospital.reservation.repository.MedicalDepartmentRepository;
import hospital.reservation.service.DoctorService;
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
public class DoctorController {

    private final DoctorService doctorService;
    private final MedicalDepartmentService medicalDepartmentService;
    private final MedicalDepartmentRepository medicalDepartmentRepository;
    
    @GetMapping("/doctors/new")
    public String createForm(Model model){
        List<MedicalDepartment> medicalDepartments = medicalDepartmentService.findMedicalDepartments();

        model.addAttribute("medicalDepartments",medicalDepartments);
        model.addAttribute("doctorForm", new DoctorForm());
        return "doctors/createDoctorForm";
    }

    @PostMapping("/doctors/new")
    public String create(@Valid DoctorForm form, BindingResult result,
                         @RequestParam("medicalDepartmentId") Long medicalDepartmentId){

        if(result.hasErrors()){
            return "doctors/createDoctorForm";
        }

        MedicalDepartment medicalDepartment = medicalDepartmentService.findOne(medicalDepartmentId);
        medicalDepartmentRepository.save(medicalDepartment);
        
        Doctor doctor = new Doctor();
        doctor.setName(form.getName());
        doctor.setCareer(form.getCareer());
        doctor.setMedicalDepartment(medicalDepartment);
        
        doctorService.register(doctor, medicalDepartment);
        return "redirect:/";
    }

    @GetMapping("/doctors")
    public String list(Model model){
        List<Doctor> doctors = doctorService.findDoctors();
        model.addAttribute("doctors",doctors);
        return "doctors/doctorList";
    }
}
