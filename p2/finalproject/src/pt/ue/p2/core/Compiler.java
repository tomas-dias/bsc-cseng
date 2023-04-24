package pt.ue.p2.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Compiler {

    private Map<Character, TurtleStatement> rules;

    public Compiler() {
        this.rules = new HashMap<>();
    }

    public Map<Character, TurtleStatement> getRules() {
        return this.rules;
    }

    public void setRules(Map<Character, TurtleStatement> rules) {
        this.rules = rules;
    }

    public void addRule(Character letter, TurtleStatement statement) {
        this.rules.put(letter, statement);
    }

    protected TurtleStatement compile(Character c) {
        return this.rules.get(c);
    }

    public Vector<TurtleStatement> compile(String word) {
        Vector<TurtleStatement> result = new Vector<>();

        for (int i = 0; i < word.length(); i++) {
            result.add(compile(word.charAt(i)));
        }

        return result;
    }
}
