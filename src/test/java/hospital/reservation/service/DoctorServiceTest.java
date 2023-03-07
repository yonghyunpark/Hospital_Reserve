package hospital.reservation.service;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.MedicalDepartment;
import hospital.reservation.repository.DoctorRepository;
import hospital.reservation.repository.MedicalDepartmentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DoctorServiceTest {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    DoctorService doctorService;
    @Autowired
    MedicalDepartment medicalDepartment;
    @Autowired
    MedicalDepartmentRepository medicalDepartmentRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    public void 의사(){
        //given
        Doctor doctor = new Doctor();
        doctor.setName("Park");
        doctor.setCareer(13L);
        MedicalDepartment medicalDepartment = new MedicalDepartment();
        medicalDepartment.setName("외과");
        medicalDepartment.setPhoneNum("010-1111-2222");

        //when
        Doctor doctor1 = doctorService.register(doctor, medicalDepartment);

        //then
        assertEquals(doctor, doctorRepository.findOne(doctor1.getId()));
    }

}
