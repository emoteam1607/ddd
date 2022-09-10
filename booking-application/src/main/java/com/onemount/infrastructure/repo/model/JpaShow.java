package com.onemount.infrastructure.repo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Getter
@Setter
@Entity
@Table(name = "tbl_show")
public class JpaShow extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @JoinColumn(name = "show_id")
    private Set<JpaSeat> seats;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "image_url")
    private String imageUrl;

    // add more properties here
    @Column(name = "description", length = 3000)
    private String description;
}
