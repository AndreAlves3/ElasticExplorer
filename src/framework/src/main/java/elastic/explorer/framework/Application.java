/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elastic.explorer.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author andre
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String [] args){
        SpringApplication.run(Application.class, args);
    }
}
