package fraglab.dbunit;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    private Util() {
    }

    public static List<String> asList(String pipeSeperatedValues) {
        if (pipeSeperatedValues == null) {
            return null;
        } else {
            return Arrays.stream(pipeSeperatedValues.split("\\|", -1)).collect(Collectors.toList());
        }
    }

}
