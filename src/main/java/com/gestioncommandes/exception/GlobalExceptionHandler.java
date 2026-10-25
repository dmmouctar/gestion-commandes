
package com.gestioncommandes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> gererErreurValidation(
            MethodArgumentNotValidException exception) {

        Map<String, Object> reponse = new HashMap<>();

        reponse.put("status", HttpStatus.BAD_REQUEST.value());
        reponse.put("error", "Erreur de validation");

        reponse.put("messages", exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erreur -> erreur.getDefaultMessage())
                .collect(Collectors.toList()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(reponse);
    }

    @ExceptionHandler(RessourceIntrouvableException.class)
    public ResponseEntity<Map<String, Object>> gererRessourceIntrouvable(
            RessourceIntrouvableException exception) {

        Map<String, Object> reponse = new HashMap<>();

        reponse.put("status", HttpStatus.NOT_FOUND.value());
        reponse.put("error", "Ressource introuvable");
        reponse.put("message", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(reponse);
    }
}




