/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiGatewayController {
    

    private RestTemplate restTemplate;

    /**
     * Recurso que se encarga de hacer el redireccionamiento de la peticion seguin el parametro que le llega en el request
     * @param operacion
     * @param numero1
     * @param numero2
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/operacion")
    public Double invocarOperacion(@RequestParam("operacion") String operacion, @RequestParam("numero1") Double numero1, @RequestParam("numero2") Double numero2){
        String urlServicios = "http://localhost";
        restTemplate = new RestTemplate();
        switch(operacion){
            case "s":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8081/sumar",numero1.toString(),numero2.toString());
                break;
            case "r":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8082/restar",numero1.toString(),numero2.toString());
                break;
            case "m":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8083/multiplicar",numero1.toString(),numero2.toString());
                break;
            case "d":
                urlServicios = String.format("%s:%s?numero1=%s&numero2=%s", urlServicios, "8084/dividir",numero1.toString(),numero2.toString());
                break;
        }
        System.out.println("urlServicios : " + urlServicios);
        return restTemplate.getForObject(urlServicios, Double.class);
    }
}
