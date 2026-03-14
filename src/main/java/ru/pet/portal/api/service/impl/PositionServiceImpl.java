package ru.pet.portal.api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pet.portal.api.service.PositionService;
import ru.pet.portal.store.entity.PositionE;
import ru.pet.portal.store.repository.PositionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Override
    public List<PositionE> getAll() {
        return positionRepository.findAll();
    }
}
