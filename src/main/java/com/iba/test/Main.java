package com.iba.test;

import com.iba.test.utils.Performer;

/*
Examples of arguments:
1) -f D:\\some_dir\\file_in.txt (double backslashes are required)
2) -cmd dir
3) -rk HKEY_CURRENT_USER\\Software\\JavaSoft\\Prefs\\jetbrains\\user_id_on_machine (double backslashes are required)
*/

public class Main {
    public static void main(String[] args) {
        if (args.length >= 2) {
            Performer performer = Performer.getPerformer(args);
            if (performer != null) {
                performer.perform();
            } else {
                System.out.println("Wrong flag.");
            }
        } else {
            System.out.println("At least two arguments should be passed to main.");
        }
    }
}
