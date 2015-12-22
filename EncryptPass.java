/*
 * Copyright (C) 2015 Gabriel Retana.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
 

import java.util.Arrays;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class EncryptPass
{
    String username;
    String password;
    byte[] key;
    byte[] encrypted;

    public EncryptPass(String pUsername, String pPassword)
    {
        username = pUsername;
        password = pPassword;
    }

    public EncryptPass()
    {
        username = "";
        password = "";
    }

    public String Encrypt()
    {
        String passenc = "";
        try {
            key = (username + password).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            encrypted = cipher.doFinal(password.getBytes());
            
            for(int i = 0; i < encrypted.length; ++i) {
                passenc = passenc + (new Integer(encrypted[i]));
            }
            
        } catch(Exception ex) {
            System.out.println("JEncryptGX Error: " + ex.getMessage());
        }
        return passenc;
    }

    public void setPassword(String pPassword)
    {
        password = pPassword;
    }

    public void setUsername(String pUsername)
    {
        username = pUsername;
    }
}
