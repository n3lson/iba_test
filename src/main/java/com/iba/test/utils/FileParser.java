package com.iba.test.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.iba.test.utils.Constants.*;

public class FileParser {
    private String filepath;

    public FileParser(String arg) {
        this.filepath = arg.substring(arg.indexOf(FILE_FLAG) + 1);
    }

    private List<String> getFileLines(String path) throws IOException {
        return Files.lines(Paths.get(path))
                    .flatMap(str -> Stream.of(str.split(Constants.NEW_LINE)))
                    .collect(Collectors.toList());
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

    public void parse() {
        File fileIn = new File(filepath);
        if (!fileIn.isAbsolute()) {
            filepath = ROOT.concat(BACKSLASH).concat(filepath);
        }
        try {
            if (fileIn.exists()) {
                String[] keyAndValue;
                StringBuilder output = new StringBuilder();
                List<String> lines = getFileLines(filepath);
                for (String line: lines) {
                    keyAndValue = getKeyAndValue(line);
                    if (keyAndValue != null) {
                        output.append(keyAndValue[0])
                              .append(NEW_LINE)
                              .append(keyAndValue[1])
                              .append(NEW_LINE);
                    } else {
                        output.append(lines);
                        break;
                    }
                }
                Files.write(Paths.get(FILE_OUT), output.toString().getBytes());
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
