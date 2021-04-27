package model;

import java.io.Serializable;

public class Package implements Serializable {

    // Переменная, определяющая тип входящего пакета
    private PackageType packageType;
    private String login;
    private String password;
    private String fileName;
    private long fileSize;

    public PackageType getPackageType() {
        return packageType;
    }
}
