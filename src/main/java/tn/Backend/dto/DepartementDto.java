package tn.Backend.dto;


import tn.Backend.entites.Departement;
import tn.Backend.form.DepartementFrom;

import java.util.List;
import java.util.stream.Collectors;

public class DepartementDto extends DepartementFrom {
    private Long id;


    public DepartementDto(Departement departement) {
        super(departement);
        this.id = departement.getId();
    }
    public static DepartementDto of(Departement departement)
    {
        return new DepartementDto(departement);
    }

    public static List<DepartementDto> of(List<Departement> departements){
        return departements.stream().map(DepartementDto::of).collect(Collectors.toList());
    }
}
