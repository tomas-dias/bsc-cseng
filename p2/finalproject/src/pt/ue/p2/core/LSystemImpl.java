package pt.ue.p2.core;

import java.util.HashMap;
import java.util.Map;

import pt.ue.p2.interfaces.LSystem;

public class LSystemImpl implements LSystem {

    private String start;
    private String constants;
    private Map<Character, String> rules;
    
    public LSystemImpl(String constants) {
        this.rules = new HashMap<>();
        this.constants = constants;
    }

    public String getConstants() {
        return this.constants;
    }

    public void setConstants(String constants) {
        this.constants = constants;
    }

    public Map<Character, String> getRules() {
        return this.rules;
    }

    public void setRules(Map<Character, String> rules) {
        this.rules = rules;
    }

    public String getStart() {
        return this.start;
    }

    @Override
    public void setStart(String start) {
        this.start = start;
    }

    @Override
    public void addRule(Character symbol, String word) {
        this.rules.put(symbol, word);
    }

    @Override
    public String iter(int n) {

        String result = this.start;
        StringBuilder word = new StringBuilder();
        String rule;

        for(int iterationNumber = 0; iterationNumber < n; iterationNumber++) {

            for(int currentSymbolPosition = 0; currentSymbolPosition < result.length(); currentSymbolPosition++) {

                char symbolToReplace = result.charAt(currentSymbolPosition);

                if(!this.constants.contains(Character.toString(symbolToReplace))) {
                    rule = rules.get(symbolToReplace);
                    word.append(rule);
                }
                else {
                    word.append(symbolToReplace);
                }
            }

            result = word.toString();
            word = new StringBuilder();
        }

        return result;
    }
}
