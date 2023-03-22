package com.example.accessingdatamongodb.init;

import com.example.accessingdatamongodb.domain.Customer;
import com.example.accessingdatamongodb.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitProcessor implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) {

        customerRepository.deleteAll();


        customerRepository.save(new Customer("Alice", "Smith"));
        customerRepository.save(new Customer("Bob", "Smith"));
        customerRepository.save(new Customer("김", "영롱"));
        customerRepository.save(new Customer("김", "수빈"));
        customerRepository.save(new Customer("이", "지원"));
        customerRepository.save(new Customer("지", "성준"));

        System.out.println("customerRepository = " + customerRepository.findAll());

        Customer customer1 = customerRepository.findByFirstName("김");
        System.out.println("customer1 = " + customer1);
        List<Customer> customers = customerRepository.findByLastName("Smith");
        System.out.println("customers = " + customers);


    }
}
