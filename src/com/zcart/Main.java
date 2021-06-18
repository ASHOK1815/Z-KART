package com.zcart;


import customer.Customer;
import filehandler.Filehandler;
import filehandler.PassswordVerifier;
import product.Shopping;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        File file = new File("./File_db/zusers_db.txt");
        Filehandler fileioworker = new Filehandler();
        PassswordVerifier passswordVerifier =new PassswordVerifier();
        Admin admin=new Admin();
        Shopping shop=new Shopping();

        char choice;

        do {
            System.out.println("Welcome to Z-kart");
            System.out.println("1:Register");
            System.out.println("2:Login");
            System.out.println("3:Password Change");
            System.out.println("Q:Quit");
            choice = scan.next().charAt(0);
            switch (choice) {

                case '1':

                    String email;
                    String firstPassword;
                    String secondPassword;
                    String name;
                    long mobileNumber;
                    System.out.println("Enter Your Mail id");
                    email = scan.next();

                    if (fileioworker.emailVerifier(email)) {
                        System.out.println("Email id already exist!. Try to login");
                        break;
                    }

                    System.out.println("Enter Your first Name");
                    name = scan.next();
                    scan.nextLine();

                    System.out.println("Enter Your Mobile Number");
                    mobileNumber = scan.nextLong();
                    scan.nextLine();
                    System.out.println("Password complexity of mandating at least 2 lower case, 2 upper case and 2 numbers with a minimum length of 6");

                    System.out.println("Enter Your Password");
                    firstPassword = scan.next();

                    System.out.println("Enter Password again");
                    secondPassword = scan.next();

                    if(!passswordVerifier.passwordValidChecker(firstPassword,secondPassword))
                    {
                        System.out.println("Password condition not match Try again!");
                        break;
                    }
                    else
                    {
                        System.out.println("Password match successfully!");

                    }

                    firstPassword = passswordVerifier.encryptPassword(firstPassword);

                    Customer customer = new Customer(email, firstPassword, name, mobileNumber);

                    fileioworker.addUser(customer,file);
                    System.out.println(customer.getName() + " added successfully");
                    break;



                case '2':

                    String loginEmail;
                    String loginPassword;

                    System.out.println("Enter Your mail id");
                    loginEmail = scan.next();
                    System.out.println("Enter password");
                    loginPassword = scan.next();
                    loginPassword = passswordVerifier.encryptPassword(loginPassword);


                    if(fileioworker.adminChecker(loginEmail,loginPassword))
                    {

                        if( loginPassword.equals("yzaaz"))
                        {

                            if(!admin.adminDefault_PasswordChanger())
                            {
                                System.out.println("Password condition not match Try again!");
                                break;
                            }
                            else
                            {
                                System.out.println("Password change Successfully !");
                            }
                        }
                        else
                        {

                            admin.displayCurrentStockDetails();
                            int adminPersmission;

                            do{
                                System.out.println("Want to Update stock and add product::--PRESS: 1   PRESS:2-QUIT");
                                adminPersmission=scan.nextInt();

                                if(adminPersmission==1)
                                {
                                       admin.adminUpdateStock();
                                }
                                else
                                {
                                        System.out.println("Thanks for checking out Stock ");
                                        System.out.println("-----------Z-KART-------------");
                                }
                            }while(adminPersmission!=2);

                        }

                    }
                    else if(fileioworker.emailAndPasswordVerifier(loginEmail,loginPassword))
                    {

                           shop.shoppingDetials(loginEmail);


                    }
                    else
                    {
                        System.out.println("Either Email not registed or Password is incorrect please try again");
                    }

                    break;


                case '3':

                    passswordVerifier.passwordUpdate();
                    break;

            }
        }while(choice!='Q');













            }


}