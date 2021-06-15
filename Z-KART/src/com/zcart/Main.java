package com.zcart;

import java.io.File;

import java.io.*;

import java.util.Scanner;


import user.Customer;
import user.UserRepository;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        File file = new File("./zusers_db.txt");
        char choice;
       do{
            System.out.println("Welcome to zkart");
            System.out.println("1:Register");
            System.out.println("2:Login");
            System.out.println("Q:Quit");
            choice =scan.next().charAt(0);
            switch(choice)
            {
                case '1':
                    String email;
                    String password;
                    String name;
                    long mobileNumber;

                    email=scan.nextLine();
                    password=scan.nextLine();
                    name=scan.nextLine();
                    mobileNumber=scan.nextLong();
                    password = userRepository.encryptPassword(password);
                    Customer customer = new Customer(email,password,name,mobileNumber);
                    userRepository.addUser(customer,file);
                    System.out.println(customer.getName()+" added successfully");
                    break;
                    case '2':
                        System.out.println("Coming soon");

            }

        }while(choice!='Q');

    }
}
