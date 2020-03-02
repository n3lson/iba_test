package com.iba.test.utils;

import java.util.Arrays;

import static com.iba.test.utils.Constants.*;

public abstract class Performer {
    public static Performer getPerformer(String[] args) {
        switch (args[0]) {
            case PARSE_FILE_FLAG:
                return new FileParser(args[1]);
            case EXEC_CMD_FLAG:
                return new CommandExecutor(Arrays.copyOfRange(args, 1, args.length));
            case READ_REGISTRY_FLAG:
                return new RegistryReader(args[1]);
            default:
                return null;
        }
    }

    public abstract void perform();
}
