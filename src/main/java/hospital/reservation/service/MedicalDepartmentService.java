package hospital.reservation.service;

import hospital.reservation.domain.Hospital;
import hospital.reservation.domain.MedicalDepartment;
import hospital.reservation.repository.HospitalRepository;
import hospital.reservation.repository.MedicalDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MedicalDepartmentService {
    
    private final MedicalDepartmentRepository medicalDepartmentRepository;
    private final HospitalRepository hospitalRepository;

    /**
     * 진료과 등록
     */
    @Transactional
    public MedicalDepartment register(Long medicalDepartmentId, Hospital hospital){
        MedicalDepartment medicalDepartment = medicalDepartmentRepository.findOne(medicalDepartmentId);
        medicalDepartment.setHospital(hospital);
        hospital.getMedicalDepartments().add(medicalDepartment);
        medicalDepartmentRepository.save(medicalDepartment);
        hospitalRepository.save(hospital);
        return medicalDepartment;
    }

    /**
     * 진료과 전체 조회
     */
    public List<MedicalDepartment> findMedicalDepartments(){
        return medicalDepartmentRepository.findAll();
    }

    public MedicalDepartment findOne(Long medicalDepartmentId){
        return medicalDepartmentRepository.findOne(medicalDepartmentId);
    }
}
