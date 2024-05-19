package org.example.collection;

public class TreeDatastructures {

    public static void main(String[] args) {
        String initialNested = """
                Rosetta Code
                ....rocks
                ........code
                ........comparison
                ........wiki
                ....mocks
                ........trolling
                """;

        System.out.println(initialNested);

        String indented = nestedToIndented(initialNested);
        System.out.println(indented);

        String finalNested = indentedToNested(indented);
        System.out.println(finalNested);

        final boolean equal = (initialNested.compareTo(finalNested) == 0);
        System.out.println("initialNested = finalNested ? " + equal);
    }

    private static String nestedToIndented(String nested) {
        StringBuilder result = new StringBuilder();

        for (String line : nested.split(LINE_END)) {
            int index = 0;
            while (line.charAt(index) == '.') {
                index += 1;
            }
            result.append(index / 4).append(" ").append(line.substring(index)).append(LINE_END);
        }

        return result.toString();
    }

    private static String indentedToNested(String indented) {
        StringBuilder result = new StringBuilder();

        for (String line : indented.split(LINE_END)) {
            final int index = line.indexOf(' ');
            final int level = Integer.parseInt(line.substring(0, index));
            result.append("....".repeat(Math.max(0, level)));
            result.append(line.substring(index + 1)).append(LINE_END);
        }

        return result.toString();
    }

    private static final String LINE_END = "\n";

}
