package hospital.reservation.service;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.Patient;
import hospital.reservation.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    /**
     * 환자 등록
     */
    @Transactional
    public Long register(Patient patient){
        patientRepository.save(patient);
        return patient.getId();
    }

    /**
     * 환자 전체 조회
     */
    public List<Patient> findPatients(){
        return patientRepository.findAll();
    }

    public Patient findOne(Long patientId){
        return patientRepository.findOne(patientId);
    }
}
