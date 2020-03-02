package com.iba.test.utils;

class Constants {
    static final String PARSE_FILE_FLAG = "-f";
    static final String EXEC_CMD_FLAG = "-cmd";
    static final String READ_REGISTRY_FLAG = "-rk";
    static final String NEW_LINE = "\n";
    static final String DELIMITER = "=";
    static final String SPACE = " ";
    static final String BACKSLASH = "\\";
    static final String EMPTY_STRING = "";
    static final String ROOT = System.getProperty("user.dir");
    static final String FILE_OUT = ROOT.concat("\\file_out.txt");
    static final String FILE_ERR = ROOT.concat("\\file_err.txt");
    static final String CMD_OUT = ROOT.concat("\\cmd_out.txt");
    static final String CMD_ERR = ROOT.concat("\\cmd_err.txt");
    static final String RK_OUT = ROOT.concat("\\rk_out.txt");
    static final String RK_ERR = ROOT.concat("\\rk_err.txt");
    static final String HKEY_CLASSES_ROOT = "HKEY_CLASSES_ROOT";
    static final String HKEY_CURRENT_USER = "HKEY_CURRENT_USER";
    static final String HKEY_LOCAL_MACHINE = "HKEY_LOCAL_MACHINE";
    static final String HKEY_USERS = "HKEY_USERS";
    static final String HKEY_CURRENT_CONFIG = "HKEY_CURRENT_CONFIG";
    static final String CMD_EXE = "cmd.exe";
    static final String CMD_ARG = "/c";

    private Constants() { }
}
