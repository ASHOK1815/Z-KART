package user;

import java.io.*;


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

   public void addUser(Customer customer,File file) throws IOException {
        FileWriter fileWriter = new FileWriter(file,true);
        BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
        bufferedWriter.write(customer.toString());
        bufferedWriter.close();
        fileWriter.close();
    }

}
