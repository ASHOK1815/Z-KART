package com.zcart;

import java.io.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import user.Customer;
import java.io.File;

import user.UserRepository;

public class Main {

    public static boolean Emailchecker(String email) throws IOException {

        File readfile = new File("./zusers_db.txt");
        BufferedReader br = new BufferedReader(new FileReader(readfile));
        String st;
        while ((st = br.readLine()) != null)
        {
            String[] data=st.split(" ");
            if(data[0].equals(email))
            {
                System.out.println("Email id alraedy exist!. Try to login");
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        UserRepository userRepository = new UserRepository();
        File file = new File("./zusers_db.txt");
        char choice;
       do{
            System.out.println("Welcome to Z-kart");
            System.out.println("1:Register");
            System.out.println("2:Login");
            System.out.println("Q:Quit");
            choice =scan.next().charAt(0);
            switch(choice)
            {
                case '1':
                    String email;
                    String password;
                    String password1;
                    String name;
                    long mobileNumber;
                    System.out.println("Enter Your Mail id");
                    email=scan.next();

                    if(Emailchecker(email))
                    {
                        break;
                    }

                    System.out.println("Enter Your Name");
                    name=scan.next();
                    System.out.println("Enter Your Mobile Number");
                    mobileNumber=scan.nextLong();
                    scan.nextLine();
                    System.out.println("Enter Your Password");
                    password=scan.nextLine();

                    System.out.println("Enter Password again");
                    password1=scan.nextLine();
                    if(password.length()!=password1.length())
                    {
                        System.out.println("Password did not match Try again!");
                        break;
                    }
                    else
                    {
                           boolean flag=true;
                           for(int i=0;i<password.length();i++)
                           {
                               if(password.charAt(i)!=password1.charAt(i))
                               {
                                   flag=false;
                                   break;
                               }
                           }

                           if(!flag)
                           {
                               System.out.println("Password did not match Try again!");
                               break;
                           }
                    }
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
