package model;

import java.io.Serializable;
import java.util.List;

// Это класс, описывающий пакеты, передающиеся
// по каналу клиент-сервер
public class Package implements Serializable {

    // Переменная, определяющая тип входящего пакета
    private PackageType packageType;
    private String login;
    private String password;
    private List<String> filesList;
    private String fileName;
    private long fileSize;
    private byte[] buffer;

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<String> filesList) {
        this.filesList = filesList;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Package(PackageType packageType) {
        this.packageType = packageType;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
