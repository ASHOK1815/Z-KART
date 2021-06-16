package com.zcart;

import java.util.LinkedHashMap;
import java.util.Map;

public class PasswordCollector {

    public LinkedHashMap<String, String> map= new LinkedHashMap<String, String>();



    void insertdata(String password,String email)
    {
        map.put(password,email);
    }


    boolean oldpasswordcheck(String email,String password)
    {
        boolean flag = false;
        for (Map.Entry<String, String> mapElement : map.entrySet()) {

            String key = mapElement.getKey();
            String value = mapElement.getValue();
            System.out.println(key);
            System.out.println(value);
            if(key.equals(password))
            {
                if(value.equals(email))
                {
                    flag=true;
                }

            }


        }

       return flag;


    }




}
