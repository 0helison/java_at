package br.com.ponteshelison.medicalconsult.user.Enum;

public enum Permission {
    ADMIN("ADMIN"),
    SECRETARY("SECRETARY");

    private final String description;

    Permission (String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
