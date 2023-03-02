package hospital.reservation.repository;

import hospital.reservation.domain.Doctor;
import hospital.reservation.repository.DoctorRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DoctorRepositoryTest {
    @Autowired
    DoctorRepository doctorRepository;

    @Test
    @Rollback(value = false)
    public void 의사테스트(){
        Doctor doctor = new Doctor();
        doctor.setName("doctorA");
        Long saveId = doctorRepository.save(doctor);

        Doctor findDoctor = doctorRepository.findOne(saveId);

        assertThat(findDoctor.getId()).isEqualTo(doctor.getId());
        assertThat(findDoctor.getName()).isEqualTo(doctor.getName());

        assertThat(findDoctor).isEqualTo(doctor);

    }

}
