package com.iba.test;

import com.iba.test.utils.CommandExecutor;
import com.iba.test.utils.FileParser;
import com.iba.test.utils.RegistryReader;
import java.util.Arrays;

import static com.iba.test.utils.Constants.*;

/*
Examples of arguments:
1) -fD:\\some_dir\\file_in.txt (double backslashes are required)
2) -cmd dir (a command such as '-cmd mkdir D:\\some_dir' can contain spaces so several args can be passed)
3) -rkHKEY_CURRENT_USER\\Software\\JavaSoft\\Prefs\\jetbrains\\user_id_on_machine (double backslashes are required)
*/

public class Main {
    public static void main(String[] args) {
        if (args.length == 0 ) {
            System.out.println("No parameters have been passed to main.");
            return;
        }

        if (args[0].startsWith(PARSE_FILE_FLAG)) {
            new FileParser(args[0]).parse();
        } else if (args[0].startsWith(EXEC_CMD_FLAG)) {
            new CommandExecutor(Arrays.copyOfRange(args, 1, args.length)).execute();
        } else if (args[0].startsWith(READ_REGISTRY_FLAG)) {
            new RegistryReader(args[0]).read();
        }
    }
}
