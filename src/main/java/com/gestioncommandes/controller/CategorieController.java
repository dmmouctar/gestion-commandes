package com.gestioncommandes.controller;

import com.gestioncommandes.entity.Categorie;
import com.gestioncommandes.service.CategorieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategorieController {

    private final CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Categorie> listerCategories() {
        return categorieService.listerCategories();
    }

    @GetMapping("/{id}")
    public Categorie trouverParId(@PathVariable Long id) {
        return categorieService.trouverParId(id);
    }

    @PostMapping
    public Categorie creerCategorie(@RequestBody Categorie categorie) {
        return categorieService.creerCategorie(categorie);
    }

    @PutMapping("/{id}")
    public Categorie modifierCategorie(
            @PathVariable Long id,
            @RequestBody Categorie categorie) {
        return categorieService.modifierCategorie(id, categorie);
    }

    @DeleteMapping("/{id}")
    public boolean supprimerCategorie(@PathVariable Long id) {
        return categorieService.supprimerCategorie(id);
    }
}
