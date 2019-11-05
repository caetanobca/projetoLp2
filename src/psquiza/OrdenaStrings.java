package psquiza;

import java.util.Comparator;

public class OrdenaStrings  implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        int comparaCodigo = o1.split(":")[0].compareToIgnoreCase(o2.split(":")[0]) * -1;

        return comparaCodigo;
    }
}
