package tn.Backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.Backend.entites.Departement;
import tn.Backend.form.DepartementFrom;
import tn.Backend.repository.DepartementRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartementServiceImpl implements DepartementService{


    @Autowired
    DepartementRepository departementRepository;
    @Override
    public Departement addDepartement(DepartementFrom form) {
        Departement departement = new Departement();
        departement.setName(form.getNameDep());
        return departementRepository.save(departement);
    }

    @Override
    public Departement updateDepartement(Long id, DepartementFrom form) {
        Departement departement = getDepartement(id);
        departement.setName(form.getNameDep());
        return departementRepository.save(departement);
    }

    @Override
    public Departement getDepartement(Long id) {
        Optional<Departement> departement = departementRepository.findById(id);
        return departement.get();
    }

    @Override
    public Map<String, Boolean> deleteDepartement(Long id) {
        Departement departement = getDepartement(id);
        departementRepository.delete(departement);
        Map<String,Boolean> map = new HashMap<>();
        map.put("deleted",Boolean.TRUE);
        return map;
    }

    @Override
    public List<Departement> getDepartement() {

        return departementRepository.findAll();
    }

}
