package com.onemount.infrastructure.adapters;

import com.onemount.domain.model.Guest;
import com.onemount.domain.ports.spi.IGuestPersistence;
import com.onemount.infrastructure.repo.JpaGuestRepository;
import com.onemount.infrastructure.repo.mappers.JpaGuestMapper;
import com.onemount.infrastructure.repo.model.JpaGuest;
import org.springframework.stereotype.Component;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Component
public class GuestJpaPersistence extends JpaPersistence<Guest, JpaGuest, Integer, JpaGuestRepository> implements IGuestPersistence {

    public GuestJpaPersistence(JpaGuestRepository repo,
                               JpaGuestMapper mapper) {
        super(repo, mapper);
    }
}
