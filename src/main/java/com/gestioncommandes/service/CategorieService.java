package com.gestioncommandes.service;

import com.gestioncommandes.entity.Categorie;
import com.gestioncommandes.repository.CategorieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> listerCategories() {
        return categorieRepository.findAll();
    }

    public Categorie trouverParId(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Categorie creerCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie modifierCategorie(Long id, Categorie categorieModifiee) {
        Categorie categorieExistante = categorieRepository.findById(id).orElse(null);
        if (categorieExistante == null) {
            return null;
        }
        categorieExistante.setNom(categorieModifiee.getNom());
        return categorieRepository.save(categorieExistante);
    }

    public boolean supprimerCategorie(Long id){
        if(!categorieRepository.existsById(id)){
            return false;
        }
        categorieRepository.deleteById(id);
        return true;
    }
}
