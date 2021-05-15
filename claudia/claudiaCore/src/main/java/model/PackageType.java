package model;

// В этом перечислении указаны все возможные типы пакетов,
// пересылаемые по каналу клиент-сервер.
public enum PackageType {

    REG, REG_OK, REG_FAIL, AUTH, AUTH_OK, AUTH_FAIL, FILE, GET_FILE, GO_TO_DIR, TO_PARENT_DIR, SHOW_FILES, MAKE_DIR
}
