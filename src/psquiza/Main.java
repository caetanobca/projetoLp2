package psquiza;

import easyaccept.EasyAccept;

public class Main {
    public static void main(String[] args) {
        args = new String[]{
                "psquiza.Facade", "AcceptTests/use_case_3.txt"
        };
        EasyAccept.main(args);
    }
}

