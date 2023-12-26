package org.example.playground;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

public class AviatorTest {

    public static void main(String[] args) {
        // Compile a script
        Expression script = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
        script.execute();

        String expression = "a-(b-c) > 100";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        Boolean result = (Boolean) compiledExp.execute(compiledExp.newEnv("a", 100.3, "b", 45, "c", -199.100));
        System.out.println(result);
    }
}
