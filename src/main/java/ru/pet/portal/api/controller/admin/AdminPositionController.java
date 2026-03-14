package ru.pet.portal.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.pet.portal.api.controller.dto.mapper.PositionMapper;
import ru.pet.portal.api.controller.dto.position.PositionResponseDto;
import ru.pet.portal.api.service.PositionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "${exam.portal.front-url}")
@RequestMapping("/api/admin/positions")
@RequiredArgsConstructor
public class AdminPositionController {

    private final PositionService positionService;
    private final PositionMapper positionMapper;

    @GetMapping
    public List<PositionResponseDto> getAll() {
        return positionMapper.toRsDtoList(positionService.getAll());
    }

}
