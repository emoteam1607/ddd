package com.onemount.infrastructure.repo.model;

import com.onemount.domain.model.enums.ReservationStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_reservation")
public class JpaReservation extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "show_id")
    private Integer showId;

    @Column(name = "seat_code")
    private String seatCode;

    @ManyToOne
    private JpaGuest guest; // guest that make a booking

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Column(name = "cancel_date")
    private LocalDate canceledDate;

    @Column(name = "booked_date")
    private LocalDate bookedDate;

    @Column(name = "canceled_reason")
    private String canceledReason;

    // add more properties here.
}
