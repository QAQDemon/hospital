package com.neusoft.ssm.util;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.Assert.*;

public class HashSaltUtilTest {

    @Test
    public void createHash() throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println(HashSaltUtil.createHash("123456"));
        System.out.println(HashSaltUtil.createHash(""));
        System.out.println(HashSaltUtil.createHash("asd"));
        System.out.println(HashSaltUtil.createHash("12sfds23fds;."));
    }
    @Test
    public void validatePassword() throws InvalidKeySpecException, NoSuchAlgorithmException {
        assertTrue(HashSaltUtil.validatePassword("123456","1000:c27b90ae8cf4d41e6c37c5bd7e38f329185d72a27c68c79d:e9867b771112277e05635914ee072e5893212a85424e18f9"));
        assertFalse(HashSaltUtil.validatePassword("23","1000:a6d425aeda8afd1ee400b4b3e8d0857483fe2295da8aecba:f2b7aab468213e392f55fcc3bfad226afa2c1a602cdeec95"));
        assertFalse(HashSaltUtil.validatePassword("sfa2","1000:a6d425aeda8afd1ee400b4b3e8d0857483fe2295da8aecba:f2b7aab468213e392f55fcc3bfad226afa2c1a602cdeec95"));
        assertFalse(HashSaltUtil.validatePassword("2afg2","1000:7ad391b7dd1f895ca3033dd12890e40d579198c502cfb499:c6d8758bf1393c9260800ca013fe811e3a1ed72db9e4412c"));
        assertFalse(HashSaltUtil.validatePassword("","1000:c27b90ae8cf4d41e6c37c5bd7e38f329185d72a27c68c79d:e9867b771112277e05635914ee072e5893212a85424e18f9"));
    }

    @Test
    public  void ttst(){
        String st="/outpatientDoctorWorkstation/searchPatient/1";
        String[] ss=st.split("/");
        System.out.println(ss[0]);
                /*
        1000:c27b90ae8cf4d41e6c37c5bd7e38f329185d72a27c68c79d:e9867b771112277e05635914ee072e5893212a85424e18f9
1000:a6d425aeda8afd1ee400b4b3e8d0857483fe2295da8aecba:f2b7aab468213e392f55fcc3bfad226afa2c1a602cdeec95
1000:17bc6e7c0236aad88cbffa5435c14f943c5f05c1f107bc6c:c97aa1974c8eac4bf6826382c11db2b65ba8ce16860b6e8c
1000:7ad391b7dd1f895ca3033dd12890e40d579198c502cfb499:c6d8758bf1393c9260800ca013fe811e3a1ed72db9e4412c
         */
    }
}