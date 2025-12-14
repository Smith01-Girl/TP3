package web2.tp3.model;

public enum UserRole{
    ADMIN("Admin"),
    JOURNALISTE("Journaliste"),
    INVITE("Invite");
    
    private final String displayName;
    
    UserRole(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
