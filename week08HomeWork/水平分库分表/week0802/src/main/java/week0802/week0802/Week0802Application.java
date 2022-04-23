package week0802.week0802;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import week0802.week0802.mappers.OrderMapper;
import week0802.week0802.models.Order;

@SpringBootApplication
public class Week0802Application {



    public static void main(String[] args) {
        SpringApplication.run(Week0802Application.class, args);

    }



}

