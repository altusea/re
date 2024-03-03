package org.example.playground;

import org.agrona.SemanticVersion;
import org.agrona.SystemUtil;
import org.agrona.collections.MutableInteger;

public class AgronaTest {

    public static void main(String[] args) {
        MutableInteger i = new MutableInteger();
        i.increment();
        System.out.println(i); // 1
        System.out.println(i.addAndGet(2)); // 3

        int semanticVersion = SemanticVersion.compose(1, 1, 0);
        System.out.println(SemanticVersion.major(semanticVersion));
        System.out.println(SemanticVersion.minor(semanticVersion));
        System.out.println(SemanticVersion.patch(semanticVersion));

        System.out.println(SystemUtil.isWindows() ? "Windows" : "Unix");
    }
}
