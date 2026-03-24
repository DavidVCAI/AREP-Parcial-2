package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {

    @GetMapping("/factors")
    public String factors(@RequestParam(value = "value", defaultValue = "1") String n) {
        int value = Integer.parseInt(n);
        String response = "{'operation':'factors', 'input':"+n+", 'output':'";
        for(int i=1; i<= value; i++){
            if ((value % i) ==0){
                response+=i;
                response+=",";
            }
        }
        response = response.substring(0, response.length()-1); // quitar la ultima coma que no se necesita
        response +="'}";
        return response;

    }

    @GetMapping("/primes")
    public String primes(@RequestParam(value = "value", defaultValue = "1") String n) {
        int value = Integer.parseInt(n);
        String response = "{'operation':'primes', 'input':"+n+" , 'output':'";
        for(int i=1; i<= value; i++){
            if (isPrime(i)){
                response+=i;
                response+=",";
            }
        }
        response +="'}";
        response = response.substring(0, response.length()-1); // quitar la ultima coma que no se necesita

        return response;

    }

    public boolean isPrime(int n){
        for(int i=2; i< n; i++){
            if ((n % i) ==0){
                return false;
            }
        }
        return true;
    }

  
}




