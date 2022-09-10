package com.onemount.infrastructure.repo;

import com.onemount.infrastructure.repo.model.JpaGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Repository
public interface JpaGuestRepository extends JpaRepository<JpaGuest, Integer> {
}
