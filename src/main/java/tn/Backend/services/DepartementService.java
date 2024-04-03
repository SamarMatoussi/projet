package tn.Backend.services;


import tn.Backend.entites.Departement;
import tn.Backend.form.DepartementFrom;

import java.util.List;
import java.util.Map;

public interface DepartementService {

    public Departement addDepartement(DepartementFrom form);
    public Departement updateDepartement(Long id, DepartementFrom form);
    public Departement getDepartement(Long id);
    public Map<String,Boolean> deleteDepartement(Long id);
    public List<Departement> getDepartement();
}
