package psquiza;

import java.util.Comparator;

public class ComparadorBusca implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        int comparaCodigo = o2.split(":")[0].compareToIgnoreCase(o1.split(":")[0]);

        return comparaCodigo;
    }
}
