package filehandler;

import customer.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;



public class PassswordVerifier {

    Scanner scan=new Scanner(System.in);
    Filehandler filehandler =new Filehandler();
    File file = new File("./File_db/zusers_db.txt");
    File recentPassword = new File("./File_db/z-Password_db.txt");



    public String encryptPassword(String password){

        StringBuffer result= new StringBuffer();

        //Applying ciserCipher algorithm for password Encryption

        for (int i=0; i<password.length(); i++)
        {
            if (Character.isUpperCase(password.charAt(i)))
            {
                char ch = (char)(((int)password.charAt(i) + 1 - 65) % 26 + 65);
                result.append(ch);
            }
            else if(password.charAt(i)>='0' && password.charAt(i)<='9')
            {
                char ch;
                int ch1=password.charAt(i)+1;
                if(ch1==58)
                {
                    ch='0';
                }
                else
                {
                    ch=(char)ch1;
                }
                result.append(ch);

            }
            else
            {
                char ch = (char)(((int)password.charAt(i) + 1 - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

    public boolean passwordValidChecker(String firstPassword ,String secondPassword)
    {
        int firstPasswordSize=firstPassword.length();
        int secondPasswordSize=secondPassword.length();

        if(firstPassword.length()!=secondPassword.length() || firstPasswordSize<6  || secondPasswordSize<6)
        {
            return  false;
        }

        int firstPassword_UpperCounter = 0, firstPassword_LowerCounter = 0, firstPassword_NumberCounter = 0;
        int secondPassword_UpperCounter = 0, secondPassword_LowerCounter = 0, secondPassword_NumberCounter = 0;



        for(int i = 0; i < firstPasswordSize; i++)
        {
            char chfirst  = firstPassword.charAt(i);
            char chsecond = secondPassword.charAt(i);

            if (chfirst >= 'A' && chfirst <= 'Z')
                firstPassword_UpperCounter++;
            else if (chfirst >= 'a' && chfirst <= 'z')
                firstPassword_LowerCounter++;
            else if (chfirst >= '0' && chfirst <= '9')
                firstPassword_NumberCounter++;

            if(chsecond >= 'A' && chsecond <= 'Z')
                secondPassword_UpperCounter++;
            else if (chfirst >= 'a' && chfirst <= 'z')
                secondPassword_LowerCounter++;
            else if (chfirst >= '0' && chfirst <= '9')
                secondPassword_NumberCounter++;


        }


        if(firstPassword_LowerCounter<2  || firstPassword_NumberCounter<2 || firstPassword_UpperCounter<2)
        {
            return false;
        }
        else if(secondPassword_LowerCounter<2  || secondPassword_NumberCounter<2 || secondPassword_UpperCounter<2)
        {
            return false;
        }

        boolean flag = true;
        for (int i = 0; i < firstPasswordSize; i++) {
            if (firstPassword.charAt(i) != secondPassword.charAt(i)) {
                    flag = false;
                    break;
            }
        }
        if (!flag) {

            return false;
        }

        return true;

    }



    public void passwordChanger( ArrayList<Customer> oldList,String password,String email)
    {
        int size=oldList.size();
        for(int i=0;i<size;i++)
        {
            if(oldList.get(i).getEmail().equals(email))
            {
                oldList.get(i).password=password;
            }
        }



        File dataUpdate= new File("./File_db/zusers_db.txt");
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(dataUpdate,true);
            PrintWriter writer = new PrintWriter(dataUpdate);
            writer.print("");
            writer.close();
            BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
            for(int i=0;i<oldList.size();i++)
            {
                bufferedWriter.write(oldList.get(i).toString());
            }

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("Password change Successfully!");
        return;

    }





    public void  passwordUpdate()
    {
        String email;
        String firstPassword;
        String secondPassword;
        String oldPassword;

        System.out.println("Enter Your Mail id");
        email = scan.next();
        System.out.println("Enter Your old Password");
        oldPassword = scan.next();
        oldPassword =encryptPassword(oldPassword);

        if (filehandler.emailAndPasswordVerifier(email,oldPassword)) {

            System.out.println("Old password match successfully");

        }
        else
        {
            System.out.println("Either email or password is incorrect please try again");
            return;
        }


        System.out.println("Password complexity of mandating at least 2 lower case, 2 upper case and 2 numbers with a minimum length of 6");

        System.out.println("Enter Your Password");
        firstPassword = scan.next();


        System.out.println("Enter Password again");
        secondPassword = scan.next();




        ArrayList<Customer> oldList=filehandler.readFileDataCustomer();



        if(!passwordValidChecker(firstPassword,secondPassword))
        {
            System.out.println("Password condition not match Try again!");
            return;
        }



        firstPassword=encryptPassword(firstPassword);

        ArrayList<Customer>oldPassswordDetails=filehandler.readFilePassword();

        int size=oldPassswordDetails.size();
        int counter=0;
        String name = null;
        long mobileNumber = 0;

        for(int i=0;i<size;i++)
        {
            if(oldPassswordDetails.get(i).getEmail().equalsIgnoreCase(email))
            {
                name=oldPassswordDetails.get(i).getName();
                mobileNumber=oldPassswordDetails.get(i).mobileNumber;
                int sizeobj=oldPassswordDetails.get(i).password.length();
                if( (firstPassword.equals(oldPassswordDetails.get(i).password) ) && (sizeobj==firstPassword.length())) {

                    System.out.println("Password match with the old password!  Try new password again!");
                    return;
                }
                else
                {
                    counter++;
                }


            }

        }

        ArrayList<Customer>temperory=new ArrayList<Customer>();
        if(counter<3)
        {
            Customer customer = new Customer(email, firstPassword, name, mobileNumber, 0);
            filehandler.addUser(customer,recentPassword);
        }
        else
        {
            boolean flag=true;
            for(int i=0;i<size;i++)
            {
                if(oldPassswordDetails.get(i).getEmail().equals(email) && flag)
                {
                    flag=false;
                    filehandler.fileDataVanisher(recentPassword);
                    continue;

                }
                else
                {
                    try{
                        FileWriter fileWriter = new FileWriter(recentPassword, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(oldPassswordDetails.get(i).toString());
                        bufferedWriter.close();
                        fileWriter.close();
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

            }

            if(flag==false)
            {
                Customer customer = new Customer(email, firstPassword, name, mobileNumber, 0);
                filehandler.addUser(customer,recentPassword);
            }

        }





        passwordChanger(oldList,firstPassword,email);

    }










}
