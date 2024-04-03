package tn.Backend.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import tn.Backend.dto.DepartementDto;
import tn.Backend.entites.Departement;
import tn.Backend.form.DepartementFrom;
import tn.Backend.services.DepartementService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/departement")
@PreAuthorize("hasRole('ADMIN')")
public class DepartementController {
    @Autowired
    DepartementService departementService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/departements")
    List<DepartementDto> getAll(){
        List<Departement> departements = departementService.getDepartement();
        return DepartementDto.of(departements);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/departement")
    public DepartementDto addDepartement(@Valid @RequestBody DepartementFrom form){
        Departement departement =  departementService.addDepartement(form);
        return DepartementDto.of(departement);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/departement/{id}")
    public DepartementDto getDepartement(@RequestParam(name = "id") Long departementId)  {
        Departement departement = departementService.getDepartement(departementId);
        return DepartementDto.of(departement);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/departement/{id}")
    public DepartementDto updateDepartement(@RequestParam(name="id") Long departementId, @Valid @RequestBody  DepartementFrom form) {
        Departement departement = departementService.updateDepartement(departementId,form);
        return DepartementDto.of(departement);

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/departement/{id}")
    public Map<String,Boolean> deleteDepartement(@RequestParam(name = "id") Long departementId) {
        return departementService.deleteDepartement(departementId);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
