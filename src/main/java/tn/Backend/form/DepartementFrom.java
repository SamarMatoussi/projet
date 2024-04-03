package tn.Backend.form;

import lombok.Getter;
import lombok.Setter;
import tn.Backend.entites.Departement;

@Getter
@Setter
public class DepartementFrom {
    private String nameDep;

    public DepartementFrom(Departement departement) {

        this.nameDep = departement.getName();
    }

    public DepartementFrom() {
    }
}
