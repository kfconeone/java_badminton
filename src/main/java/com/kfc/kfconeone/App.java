package com.kfc.kfconeone;


import com.kfc.kfconeone.firebase.FireBase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {
        //FireBase.Init();
        SpringApplication.run(App.class, args);
    }
}