package pt.ue.p2.core;

import java.util.Map;

import pt.ue.p2.interfaces.Interpreter;

public class Program {

    public static void run(String word, Map<Character, TurtleStatement> rules, Interpreter interpreter) {
        
        Compiler compiler = new Compiler();

        rules.keySet().forEach(key -> compiler.addRule(key, rules.get(key)));

        interpreter.run(compiler.compile(word));
    }
}
