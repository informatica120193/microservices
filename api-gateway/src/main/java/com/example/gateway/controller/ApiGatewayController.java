/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiGatewayController {
    
    @Value("url.servicios")
    private String urlServicios;
    private RestTemplate restTemplate;
    
    @GetMapping("/operacion")
    public Double invocarOperacion(@RequestParam("operacion") String operacion, @RequestParam("numero1") Double numero1, @RequestParam("numero2") Double numero2){
        restTemplate = new RestTemplate();
        switch(operacion){
            case "+":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8081/sumar",numero1.toString(),numero2.toString());
                break;
            case "-":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8082/restar",numero1.toString(),numero2.toString());
                break;
            case "*":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8083/multiplicar",numero1.toString(),numero2.toString());
                break;
            case "/":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8084/dividir",numero1.toString(),numero2.toString());
                break;
        }
        return restTemplate.getForObject(urlServicios, Double.class);
    }
}
