package com.iba.test.utils;

import com.sun.deploy.association.utility.WinRegistryWrapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static com.iba.test.utils.Constants.*;

public class RegistryReader {
    private String section;
    private String path;
    private String key;

    public RegistryReader(String arg) {
        try {
            String[] parsedPath = parseRegistryPath(arg.substring(arg.indexOf(REGISTRY_FLAG) + 1));
            if (parsedPath != null) {
                this.section = parsedPath[0];
                this.path = parsedPath[1];
                this.key = parsedPath[2];
            } else {
                Files.write(Paths.get(RK_ERR), "Wrong argument passed.".getBytes());
            }
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            try {
                Files.write(Paths.get(RK_ERR), "Wrong argument passed".getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, Integer> getSections() {
        HashMap<String, Integer> sections = new HashMap<>();
        sections.put(HKEY_CLASSES_ROOT, WinRegistryWrapper.HKEY_CLASSES_ROOT);
        sections.put(HKEY_CURRENT_USER, WinRegistryWrapper.HKEY_CURRENT_USER);
        sections.put(HKEY_CURRENT_CONFIG, WinRegistryWrapper.HKEY_CURRENT_CONFIG);
        sections.put(HKEY_LOCAL_MACHINE, WinRegistryWrapper.HKEY_LOCAL_MACHINE);
        sections.put(HKEY_USERS, WinRegistryWrapper.HKEY_USERS);
        return sections;
    }

    private String[] parseRegistryPath(String arg) throws StringIndexOutOfBoundsException {
        int indexOfSection = arg.indexOf(BACKSLASH);
        int indexOfKey = arg.lastIndexOf(BACKSLASH);
        String section = arg.substring(0, indexOfSection);
        String path = arg.substring(indexOfSection + 2, indexOfKey + 1);
        String key = arg.substring(indexOfKey + 1);
        if (!section.equals(EMPTY_STRING) && !path.equals(EMPTY_STRING) && !key.equals(EMPTY_STRING)) {
            return new String[]{section, path, key};
        } else {
            return null;
        }
    }

    public void read() {
        HashMap<String, Integer> sections = getSections();
        try {
            String value = WinRegistryWrapper.WinRegQueryValueEx(sections.get(section), path, key);
            if (value != null) {
                String output = key + DELIMITER + value;
                Files.write(Paths.get(RK_OUT), output.getBytes());
            } else {
                Files.write(Paths.get(RK_ERR), "No key found.".getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
