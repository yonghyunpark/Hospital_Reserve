package hospital.reservation.controller;

import hospital.reservation.domain.Address;
import hospital.reservation.domain.Hospital;
import hospital.reservation.service.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;

    @GetMapping("/hospitals/new")
    public String createForm(Model model){
        model.addAttribute("hospitalForm", new HospitalForm());
        return "hospitals/createHospitalForm";
    }

    @PostMapping("/hospitals/new")
    public String create(@Valid HospitalForm form, BindingResult result){

        if(result.hasErrors()){
            return "hospitals/createHospitalForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Hospital hospital = new Hospital();
        hospital.setName(form.getName());
        hospital.setAddress(address);

        hospitalService.register(hospital);
        return "redirect:/";
    }

    @GetMapping("/hospitals")
    public String list(Model model){
        List<Hospital> hospitals = hospitalService.findHospitals();
        model.addAttribute("hospitals", hospitals);
        return "hospitals/hospitalList";
    }
}
