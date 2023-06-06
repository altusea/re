package org.example.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtil {

    public static String removeWhitespaces(String input) {
        return Arrays.stream(input.split("\n")).map(String::trim).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        var input = """               
                mvn archetype:generate
                    -DarchetypeGroupId=org.apache.flink
                    -DarchetypeArtifactId=flink-walkthrough-datastream-java
                    -DarchetypeVersion=1.16.0
                    -DgroupId=frauddetection
                    -DartifactId=frauddetection
                    -Dversion=0.1
                    -Dpackage=spendreport
                    -DinteractiveMode=false
                """;

        System.out.println(input.split("\n").length);
        System.out.println(removeWhitespaces(input));
    }
}
