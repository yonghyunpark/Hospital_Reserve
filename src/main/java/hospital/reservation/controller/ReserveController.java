package hospital.reservation.controller;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.Patient;
import hospital.reservation.domain.Reserve;
import hospital.reservation.service.DoctorService;
import hospital.reservation.service.PatientService;
import hospital.reservation.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReserveController {

    private final ReserveService reserveService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/reserve")
    public String createForm(Model model){
        List<Patient> patients = patientService.findPatients();
        List<Doctor> doctors = doctorService.findDoctors();

        model.addAttribute("patients",patients);
        model.addAttribute("doctors",doctors);
        return "reserves/reserveForm";
    }

    @PostMapping("/reserve")
    public String reserve(@RequestParam("patientId") Long patientId,
                          @RequestParam("doctorId") Long doctorId){
        reserveService.reserve(patientId,doctorId);
        return "redirect:/";
    }

    @GetMapping("/reserves")
    public String list(Model model){
        List<Reserve> reserves = reserveService.findReserves();
        model.addAttribute("reserves", reserves);
        return "reserves/reserveList";
    }


    @PostMapping("/reserves/{reserveId}/cancel")
    public String cancelReserve(@PathVariable("reserveId") Long reserveId){
        reserveService.reserveCancel(reserveId);
        return "redirect:/reserves";
    }
}
