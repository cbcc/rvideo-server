package cn.scau.rvideo.server.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

    public static List<String> fromString(String str) {
        String[] strs = str.split("[;,|]");
        return Arrays.stream(strs).filter(s -> !s.equals("")).collect(Collectors.toList());
    }
}
