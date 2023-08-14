package ba.unsa.etf.rpr.domain;

public abstract class LoggableUser {

    private String username = null;
    private String passwordHash = null;

    public final String getUsername(){
        return username;
    }

    public final String getPasswordHash(){
        return passwordHash;
    }

    public final void setUsername(String username){
        this.username = username;
    }

    public final void setPasswordHash(String password){
        this.passwordHash = hashedPassword(password);
    }

    public abstract String hashedPassword(String password);
}
