package model;

// В этом перечислении указаны все возможные типы пакетов,
// пересылаемые по каналу клиент-сервер (будет пополняться).
public enum PackageType {

    REG, AUTH, MESSAGE, FILE, CREATE_DIR, GO_TO_DIR;
}
