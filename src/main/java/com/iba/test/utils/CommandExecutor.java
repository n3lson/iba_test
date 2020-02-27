package com.iba.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.iba.test.utils.Constants.*;

public class CommandExecutor {
    private String command;

    public CommandExecutor(String[] args) {
        this.command = parseCommand(args);
    }

    private String parseCommand(String[] args) {
        StringBuilder cmd = new StringBuilder();
        for (String arg: args) {
            cmd.append(arg).append(SPACE);
        }
        return cmd.toString();
    }

    private String readInputStream(BufferedReader reader) throws IOException {
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append(NEW_LINE);
        }
        reader.close();
        return output.toString();
    }

    private String getCommandOutput(Process proc) throws IOException {
        return readInputStream(new BufferedReader(new InputStreamReader(proc.getInputStream())));
    }

    private String getCommandError(Process proc) throws IOException {
        return readInputStream(new BufferedReader(new InputStreamReader(proc.getErrorStream())));
    }

    public void execute() {
        try {
            Process proc = new ProcessBuilder(CMD_EXE, CMD_ARG, command).start();
            String output = getCommandOutput(proc);
            String err = getCommandError(proc);
            if (!output.equals(EMPTY_STRING)) {
                Files.write(Paths.get(CMD_OUT), output.getBytes());
            } else if (!err.equals(EMPTY_STRING)) {
                Files.write(Paths.get(CMD_ERR), err.getBytes());
            }
            proc.destroy();
        } catch (IOException err) {
            try {
                Files.write(Paths.get(CMD_ERR), err.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
