package com.onemount.infrastructure.adapters;

import com.onemount.domain.model.Show;
import com.onemount.domain.ports.spi.IShowPersistence;
import com.onemount.infrastructure.repo.JpaShowRepository;
import com.onemount.infrastructure.repo.mappers.JpaShowMapper;
import com.onemount.infrastructure.repo.model.JpaShow;
import org.springframework.stereotype.Component;

/**
 * author: anct
 * date: 10/9/2022
 * YNWA
 */
@Component
public class ShowJpaPersistence
        extends JpaPersistence<Show, JpaShow, Integer, JpaShowRepository> implements IShowPersistence {

    public ShowJpaPersistence(JpaShowRepository repo,
                              JpaShowMapper mapper) {
        super(repo, mapper);
    }
}
