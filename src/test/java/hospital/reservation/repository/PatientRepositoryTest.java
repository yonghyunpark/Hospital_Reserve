package hospital.reservation.repository;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.Patient;
import hospital.reservation.repository.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PatientRepositoryTest {

    @Autowired
    PatientRepository patientRepository;

    @Test
    @Rollback(value = false)
    public void 환자테스트(){
        Patient patient = new Patient();
        patient.setName("patientA");
        Long saveId = patientRepository.save(patient);

        Patient findPatient = patientRepository.findOne(saveId);

        assertThat(findPatient.getId()).isEqualTo(patient.getId());
        assertThat(findPatient.getName()).isEqualTo(patient.getName());

        assertThat(findPatient).isEqualTo(patient);

    }
}
