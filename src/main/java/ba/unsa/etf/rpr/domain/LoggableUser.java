package ba.unsa.etf.rpr.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public abstract class LoggableUser {

    private static String DEFAULT_PASSWORD = "12345678";
    private static String HASHING_ALGORITHM = "SHA-256";
    private String username = null;
    private String passwordHash;

    {
        try {
            passwordHash = hashedPassword(DEFAULT_PASSWORD);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // my own
        }
    }

    public final String getUsername() {
        return username;
    }

    public final String getPasswordHash() {
        return passwordHash;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final void setPasswordHash(String password) throws NoSuchAlgorithmException {
        this.passwordHash = hashedPassword(password);
    }

    public String hashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);
        byte[] hashed = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashed) {
            sb.append(b);
        }
        return sb.toString();

    }

}
