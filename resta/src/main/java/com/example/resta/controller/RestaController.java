/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.resta.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author estudiantes
 */
@RestController
public class RestaController {
    
    @GetMapping("/restar")
    public Double restar(@RequestParam("numero1") Double numero1, @RequestParam("numero2") Double numero2){
        return numero1 - numero2;
    }
}
