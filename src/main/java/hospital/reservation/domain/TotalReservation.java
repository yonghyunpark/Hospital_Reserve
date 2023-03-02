package hospital.reservation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

//DB에 보여주기위한 엔티티
@Entity
@Getter @Setter
public class TotalReservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "total_reservation_id")
    private Long id;

    private String patientName;
    private String hospitalName;
    private String medicalDepartmentName;
    private String doctorName;
    private LocalDateTime reserveTime;

    @Enumerated(EnumType.STRING)
    private ReserveStatus reserveStatus;

}
