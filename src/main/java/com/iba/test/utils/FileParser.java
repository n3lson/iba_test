package com.iba.test.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.iba.test.utils.Constants.*;

public class FileParser extends Performer {
    private String filepath;

    FileParser(String filepath) {
        this.filepath = filepath;
    }

    private String getFileContent(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    private String[] getKeyAndValue(String line) {
        int indexOfDelimiter = line.indexOf(Constants.DELIMITER);
        if (indexOfDelimiter != -1) {
            return new String[]{
                    line.substring(0, indexOfDelimiter).trim(),
                    line.substring(indexOfDelimiter + 1).trim()
            };
        } else return null;
    }

    public void perform() {
        File fileIn = new File(filepath);
        if (!fileIn.isAbsolute()) {
            filepath = ROOT.concat(BACKSLASH).concat(filepath);
        }
        try {
            if (fileIn.exists()) {
                String content = getFileContent(filepath);
                String[] lines = content.split(NEW_LINE);
                String[] keyAndValue;
                StringBuilder output = new StringBuilder();
                for (String line: lines) {
                    keyAndValue = getKeyAndValue(line);
                    if (keyAndValue != null) {
                        output.append(keyAndValue[0])
                              .append(NEW_LINE)
                              .append(keyAndValue[1])
                              .append(NEW_LINE);
                    } else {
                        Files.write(Paths.get(FILE_OUT), content.getBytes());
                        return;
                    }
                }
                Files.write(Paths.get(FILE_OUT), output.toString().getBytes());
            } else {
                Files.write(Paths.get(FILE_ERR), "File not found.".getBytes());
            }
        } catch (IOException err) {
            try {
                Files.write(Paths.get(FILE_ERR), err.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
