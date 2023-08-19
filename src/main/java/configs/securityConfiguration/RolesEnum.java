package configs.securityConfiguration;

import org.jetbrains.annotations.NotNull;

public enum RolesEnum {
    USER(0);

    private int value;

    RolesEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static @NotNull RolesEnum valueOf(int value) {
        for (RolesEnum values : RolesEnum.values()) {
            if (values.getValue() == value) return values;
        }
        throw new IllegalArgumentException("Illegal argument of enum");
    }
}
