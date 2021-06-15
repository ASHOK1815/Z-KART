package user;

import Inventory.Cart;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserRepository {

   public String encryptPassword(String password){

       StringBuffer result= new StringBuffer();
       for (int i=0; i<password.length(); i++)
       {
           if (Character.isUpperCase(password.charAt(i)))
           {
               char ch = (char)(((int)password.charAt(i) +
                       1 - 65) % 26 + 65);
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
               char ch = (char)(((int)password.charAt(i) +
                       1 - 97) % 26 + 97);
               result.append(ch);
           }
       }
       return result.toString();
   }

    public String decryptPassword(String password){

        StringBuffer result= new StringBuffer();
        for (int i=0; i<password.length(); i++)
        {
            if (Character.isUpperCase(password.charAt(i)))
            {
                char ch = (char)(((int)password.charAt(i) +
                        25 - 65) % 26 + 65);
                result.append(ch);
            }
            else
            {
                char ch = (char)(((int)password.charAt(i) +
                        25 - 97) % 26 + 97);
                result.append(ch);
            }
        }
        return result.toString();
    }

   public  void addUser(Customer customer,File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
        bufferedWriter.write(customer.toString());
        bufferedWriter.close();
        fileWriter.close();
    }




    public static void addCart(Cart cart,File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
        bufferedWriter.write(cart.toString());
        bufferedWriter.close();
        fileWriter.close();
    }

}
