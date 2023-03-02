package hospital.reservation.service;

import hospital.reservation.domain.Hospital;
import hospital.reservation.repository.HospitalRepository;
import hospital.reservation.repository.MedicalDepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final MedicalDepartmentRepository medicalDepartmentRepository;

    /**
     * 병원 등록
     */
    @Transactional
    public Long register(Hospital hospital){
        hospitalRepository.save(hospital);
        return hospital.getId();
    }

    /**
     * 병원 전체 조회
     */
    public List<Hospital> findHospitals(){
        return hospitalRepository.findAll();
    }

    public Hospital findOne(Long hospitalId){
        return hospitalRepository.findOne(hospitalId);
    }

}
