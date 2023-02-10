package org.example.codewar;

import java.util.ArrayList;

class Metro {

    public static int countPassengers(ArrayList<int[]> stops) {
        return stops.stream().mapToInt(x -> x[0] - x[1]).sum();
    }
}
