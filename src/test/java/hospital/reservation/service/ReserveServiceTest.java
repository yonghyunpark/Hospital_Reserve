package hospital.reservation.service;

import hospital.reservation.domain.*;
import hospital.reservation.repository.ReserveRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReserveServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    ReserveRepository reserveRepository;
    @Autowired
    ReserveService reserveService;

    @Test
    @Rollback(false)
    public void 예약(){
        //given
        Patient patient = createPatient("김환자",18L,Gender.남성);
        Hospital hospital = createHospital("병원1",new Address("서울시","111","222"));
        MedicalDepartment medicalDepartment = createMedicalDepartment("외과", "010-1111-2222", hospital);
        Doctor doctor = createDoctor("박의사",30L, medicalDepartment);

        //when
        Long reserveId = reserveService.reserve(patient.getId(), doctor.getId());

        //then
        Reserve getReserve = reserveRepository.findOne(reserveId);

        assertEquals("예약시 상태는 RESERVE", ReserveStatus.RESERVE, getReserve.getReserveStatus());
        assertEquals("예약한 부서가 정확해야 한다", "외과", medicalDepartment.getName());
    }

    public Patient createPatient(String name, Long age, Gender gender){
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        em.persist(patient);
        return patient;
    }

    public Doctor createDoctor(String name, Long career, MedicalDepartment medicalDepartment){
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setCareer(career);
        doctor.setMedicalDepartment(medicalDepartment);
        em.persist(doctor);
        return doctor;
    }

    public Hospital createHospital(String name, Address address){
        Hospital hospital = new Hospital();
        hospital.setName(name);
        hospital.setAddress(address);
        em.persist(hospital);
        return hospital;
    }

    public MedicalDepartment createMedicalDepartment(String name, String phoneNum, Hospital hospital){
        MedicalDepartment medicalDepartment = new MedicalDepartment();
        medicalDepartment.setName(name);
        medicalDepartment.setPhoneNum(phoneNum);
        medicalDepartment.setHospital(hospital);
        em.persist(medicalDepartment);
        return medicalDepartment;
    }

}
