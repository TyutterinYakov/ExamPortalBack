package portal.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import portal.api.dto.CategoryDto;
import portal.api.dto.group.Create;
import portal.api.dto.group.Update;
import portal.api.service.CategoryService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:4200/")
public class AdminCategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> add(@RequestBody @Validated(Create.class) CategoryDto categoryDto) {
        categoryService.add(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> update(@RequestBody @Validated(Update.class) CategoryDto categoryDto) {
        categoryService.update(categoryDto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{categoryId}")
    @PreAuthorize("hasAuthority('developers:write')")
    public ResponseEntity<Void> deleteById(@PathVariable("categoryId") long categoryId) {
        categoryService.deleteById(categoryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
