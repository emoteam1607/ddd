package com.onemount.infrastructure.repo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_guest")
public class JpaGuest extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email; //using to determine guest

    @Column(name = "phone")
    private String phone;
}
