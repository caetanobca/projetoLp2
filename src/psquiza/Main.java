package psquiza;

import easyaccept.EasyAccept;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        args = new String[]{


                "psquiza.Facade", "AcceptTests/use_case_1.txt", "AcceptTests/use_case_2.txt",
                "AcceptTests/use_case_3.txt", "AcceptTests/use_case_4.txt", "AcceptTests/use_case_5.txt",
                "AcceptTests/use_case_6.txt", "AcceptTests/use_case_7.txt", "AcceptTests/use_case_8.txt",
                "AcceptTests/use_case_9.txt", "AcceptTests/use_case_10.txt", "AcceptTests/use_case_11.txt"
        };
        EasyAccept.main(args);
    }
}