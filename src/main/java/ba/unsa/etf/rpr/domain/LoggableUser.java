package ba.unsa.etf.rpr.domain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Abstract class, representing user logging in the application with provided credentials.
 */
public abstract class LoggableUser {

    private static String DEFAULT_PASSWORD = "12345678";
    private static String HASHING_ALGORITHM = "SHA-256";
    private String username = null;
    private String passwordHash;

    private Boolean admin;

    {
        try {
            passwordHash = hashedPassword(DEFAULT_PASSWORD);
            admin = false;
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

    public final void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Method for hashing the password with SHA-256 algorithm and preparing it for storing in the database.
     * @param password
     */
    public static String hashedPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);
        byte[] hashed = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashed) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }
    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
