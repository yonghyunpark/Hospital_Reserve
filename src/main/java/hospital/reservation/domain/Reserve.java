package hospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserve")
@Getter @Setter
public class Reserve {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserve_id")
    private Long id;

    private LocalDateTime reserveTime; //진료일 선택(일단 현재시간으로 설정)

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    //==연관관계 메서드==

    public void setPatient(Patient patient){
        this.patient = patient;
        patient.getReserves().add(this);
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
        doctor.getReserves().add(this);
    }

    //==생성 메서드
    public static Reserve createReserve(Patient patient, Doctor doctor){
        Reserve reserve = new Reserve();
        reserve.setPatient(patient);
        reserve.setDoctor(doctor);
        reserve.reserveTime = LocalDateTime.now();
        reserve.setReserveStatus(ReserveStatus.RESERVE);
        return reserve;
    }

    //==비즈니스 로직
    /**
     * 예약 취소
     */
    public void cancel(){
        this.setReserveStatus(ReserveStatus.CANCEL);
    }
}
