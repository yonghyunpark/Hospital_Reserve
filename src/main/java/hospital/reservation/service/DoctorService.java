package hospital.reservation.service;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.MedicalDepartment;
import hospital.reservation.repository.DoctorRepository;
import hospital.reservation.repository.MedicalDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final MedicalDepartmentRepository medicalDepartmentRepository;

    /**
     * 의사 등록
     */
    @Transactional
    public Doctor register(Long doctorId, MedicalDepartment medicalDepartment){
        Doctor doctor = doctorRepository.findOne(doctorId);
        doctor.setMedicalDepartment(medicalDepartment);
        medicalDepartment.getDoctors().add(doctor);
        doctorRepository.save(doctor);
        medicalDepartmentRepository.save(medicalDepartment);
        return doctor;
    }

    /**
     * 의사 전체 조회
     */
    public List<Doctor> findDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor findOne(Long doctorId){
        return doctorRepository.findOne(doctorId);
    }
}
