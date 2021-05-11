package model;

// В этом перечислении указаны все возможные типы пакетов,
// пересылаемые по каналу клиент-сервер (будет пополняться).
public enum PackageType {

    REG, REG_OK, REG_FAIL, AUTH, AUTH_OK, AUTH_FAIL, FILE, CREATE_DIR, GO_TO_DIR, SHOW_FILES, MAKE_DIR
}
