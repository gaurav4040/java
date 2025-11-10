package org.util;

import org.mindrot.jbcrypt.BCrypt;

public class User_Service_Util {

    public static String hashPass(String pass){
        return BCrypt.hashpw(pass,BCrypt.gensalt());
    }

    public static Boolean checkPass(String pass,String hash_pass){
        return BCrypt.checkpw(pass,hash_pass);
    }

}
