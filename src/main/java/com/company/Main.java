package com.company;

import java.lang.Math; // headers MUST be above the first class
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;


public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {
        // write your code here
        System.out.println("HW");
        ConfHelper confHelper = new ConfHelper();

        System.out.println(confHelper);
    }

}
