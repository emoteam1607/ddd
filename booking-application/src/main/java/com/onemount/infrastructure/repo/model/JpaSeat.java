package com.onemount.infrastructure.repo.model;

import com.onemount.domain.model.enums.SeatStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Author: anct
 * Date: 10/09/2022
 * #YWNA
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_seat")
@EqualsAndHashCode(of = "id", callSuper = false)
public class JpaSeat extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "show_id")
    private Integer showId;

    @Column(name = "seat_code")
    private String code;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Column(name = "booked_date")
    private LocalDate bookedDate; //duplicate for no join

    // add more properties here
}
