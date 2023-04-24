package pt.ue.p2.lsystems;

import java.util.HashMap;
import java.util.Map;

import pt.ue.p2.core.LSystemImpl;
import pt.ue.p2.core.Program;
import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;
import pt.ue.p2.interfaces.LSystem;
import pt.ue.p2.interfaces.LSystemExample;
import pt.ue.p2.statements.Forward;
import pt.ue.p2.statements.Leap;

public class CantorSet implements LSystemExample {
    
    private int iterations;
    private Interpreter interpreter;

    public CantorSet(int iterations, Interpreter interpreter) {
        this.iterations = iterations;
        this.interpreter = interpreter;
    }

    public int getIterations() {
        return this.iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public Interpreter getInterpreter() {
        return this.interpreter;
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    @Override
    public void execute() {

        LSystem cantorSet = new LSystemImpl("");
        cantorSet.setStart("A");
        cantorSet.addRule('A', "ABA");
        cantorSet.addRule('B', "BBB");

        Map<Character, TurtleStatement> rules = new HashMap<>();
        rules.put('A', new Forward(10));
        rules.put('B', new Leap(10));

        String word = cantorSet.iter(this.iterations);

        System.out.println("Cantor Set");
        System.out.println(word);
        System.out.println();

        Program.run(word, rules, this.interpreter);
    }
}
