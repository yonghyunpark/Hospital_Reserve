package hospital.reservation.service;

import hospital.reservation.domain.Doctor;
import hospital.reservation.domain.Patient;
import hospital.reservation.domain.Reserve;
import hospital.reservation.repository.DoctorRepository;
import hospital.reservation.repository.PatientRepository;
import hospital.reservation.repository.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReserveService {

    private final ReserveRepository reserveRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    /**
     * 예약
     */
    @Transactional
    public Long reserve(Long patientId, Long doctorId) {

        Patient patient = patientRepository.findOne(patientId);
        Doctor doctor = doctorRepository.findOne(doctorId);

        Reserve reserve = Reserve.createReserve(patient, doctor);

        reserveRepository.save(reserve);

        return reserve.getId();
    }

    public List<Reserve> findReserves(){
        return reserveRepository.findAll();
    }

    /**
     * 예약 취소
     */
    @Transactional
    public void reserveCancel(Long reserveId) {
        Reserve reserve = reserveRepository.findOne(reserveId);
        reserve.cancel();
    }
}
