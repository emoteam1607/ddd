package com.onemount.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Guest {

    private Integer id;

    private String firstName;

    private String lastName;

    private String fullName;

    private String phone;

    private String email;
}
