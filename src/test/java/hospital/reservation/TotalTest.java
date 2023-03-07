package hospital.reservation;

import hospital.reservation.domain.*;
import hospital.reservation.repository.*;
import hospital.reservation.service.DoctorService;
import hospital.reservation.service.MedicalDepartmentService;
import hospital.reservation.service.PatientService;
import hospital.reservation.service.ReserveService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TotalTest {

    @Autowired PatientRepository patientRepository;
    @Autowired PatientService patientService;
    @Autowired DoctorRepository doctorRepository;
    @Autowired DoctorService doctorService;
    @Autowired ReserveRepository reserveRepository;
    @Autowired ReserveService reserveService;
    @Autowired MedicalDepartmentRepository medicalDepartmentRepository;
    @Autowired MedicalDepartmentService medicalDepartmentService;
    @Autowired HospitalRepository hospitalRepository;
    @Autowired EntityManager em;

    @Test
    @Rollback(false)
    public void 전체테스트(){

        //환자
        Patient patient1 = createPatient("김환자", 20L, Gender.남성);
        Patient patient2 = createPatient("박환자", 22L, Gender.여성);
        Patient patient3 = createPatient("이환자", 15L, Gender.남성);
        Patient patient4 = createPatient("나환자", 17L, Gender.여성);

        //병원
        Hospital hospital1 = createHospital("서울병원",new Address("서울시","street1","1-1"));
        Hospital hospital2 = createHospital("안산병원",new Address("안산시","street2","1-2"));
        Hospital hospital3 = createHospital("구리병원",new Address("구리시","street3","1-3"));

        //진료과
        MedicalDepartment medicalDepartment1 = createMedicalDepartment("정형외과","111-1111");
        medicalDepartmentService.register(medicalDepartment1,hospital1.getId());
        MedicalDepartment medicalDepartment2 = createMedicalDepartment("신장내과","222-2222");
        medicalDepartmentService.register(medicalDepartment2,hospital1.getId());
        MedicalDepartment medicalDepartment3 = createMedicalDepartment("안과","333-3333");
        medicalDepartmentService.register(medicalDepartment3, hospital2.getId());
        MedicalDepartment medicalDepartment4 = createMedicalDepartment("신경외과","444-4444");
        medicalDepartmentService.register(medicalDepartment4, hospital2.getId());
        MedicalDepartment medicalDepartment5 = createMedicalDepartment("비뇨기과","555-5555");
        medicalDepartmentService.register(medicalDepartment5, hospital3.getId());
        MedicalDepartment medicalDepartment6 = createMedicalDepartment("성형외과","666-6666");
        medicalDepartmentService.register(medicalDepartment6, hospital3.getId());

        //의사
        Doctor doctor1 = createDoctor("나의사",8L);
        doctorService.register(doctor1,medicalDepartment1);
        Doctor doctor2 = createDoctor("이의사",10L);
        doctorService.register(doctor2,medicalDepartment2);
        Doctor doctor3 = createDoctor("김의사",5L);
        doctorService.register(doctor3,medicalDepartment3);
        Doctor doctor4 = createDoctor("박의사",7L);
        doctorService.register(doctor4,medicalDepartment4);
        Doctor doctor5 = createDoctor("황의사",4L);
        doctorService.register(doctor5,medicalDepartment5);
        Doctor doctor6 = createDoctor("송의사",6L);
        doctorService.register(doctor6,medicalDepartment6);

        //예약
        Long reserve1 = reserveService.reserve(patient1.getId(), doctor4.getId());
        TotalReservation totalReservation1 = createReserve(reserveRepository.findOne(reserve1));
        Long reserve2 = reserveService.reserve(patient2.getId(), doctor2.getId());
        createReserve(reserveRepository.findOne(reserve2));
        Long reserve3 = reserveService.reserve(patient3.getId(), doctor6.getId());
        createReserve(reserveRepository.findOne(reserve3));
        Long reserve4 = reserveService.reserve(patient4.getId(), doctor5.getId());
        createReserve(reserveRepository.findOne(reserve4));
        Long reserve5 = reserveService.reserve(patient1.getId(), doctor3.getId());
        TotalReservation totalReservation5 = createReserve(reserveRepository.findOne(reserve5));
        Long reserve6 = reserveService.reserve(patient1.getId(), doctor3.getId());
        createReserve(reserveRepository.findOne(reserve6));

        //예약취소
        reserveService.reserveCancel(reserve1);
        cancelTotalReserve(totalReservation1);
        reserveService.reserveCancel(reserve5);
        cancelTotalReserve(totalReservation5);
    }
    
    public Patient createPatient(String name, Long age, Gender gender){
        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        em.persist(patient);
        return patient;
    }

    public Doctor createDoctor(String name, Long career){
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setCareer(career);
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

    public MedicalDepartment createMedicalDepartment(String name, String phoneNum){
        MedicalDepartment medicalDepartment = new MedicalDepartment();
        medicalDepartment.setName(name);
        medicalDepartment.setPhoneNum(phoneNum);
        em.persist(medicalDepartment);
        return medicalDepartment;
    }

    //DB에 전체테이블을 보여주기 위한 메서드
    public TotalReservation createReserve(Reserve reserve){
        TotalReservation totalReservation = new TotalReservation();
        totalReservation.setPatientName(reserve.getPatient().getName());
        totalReservation.setHospitalName(reserve.getDoctor().getMedicalDepartment().getHospital().getName());
        totalReservation.setMedicalDepartmentName(reserve.getDoctor().getMedicalDepartment().getName());
        totalReservation.setDoctorName(reserve.getDoctor().getName());
        totalReservation.setReserveTime(reserve.getReserveTime());
        totalReservation.setReserveStatus(reserve.getReserveStatus());
        em.persist(totalReservation);
        return totalReservation;
    }
    public void cancelTotalReserve(TotalReservation totalReservation){
        totalReservation.setReserveStatus(ReserveStatus.CANCEL);
    }
}
