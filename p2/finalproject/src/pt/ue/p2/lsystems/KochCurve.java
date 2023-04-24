package pt.ue.p2.lsystems;

import pt.ue.p2.core.LSystemImpl;
import pt.ue.p2.core.Program;
import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;
import pt.ue.p2.interfaces.LSystem;
import pt.ue.p2.interfaces.LSystemExample;
import pt.ue.p2.statements.Forward;
import pt.ue.p2.statements.Turn;

import java.util.HashMap;
import java.util.Map;

public class KochCurve implements LSystemExample {
    
    private int iterations;
    private Interpreter interpreter;

    public KochCurve(int iterations, Interpreter interpreter) {
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
        
        LSystem kochCurve = new LSystemImpl("+-");
        kochCurve.setStart("F");
        kochCurve.addRule('F', "F+F-F-F+F");

        Map<Character, TurtleStatement> rules = new HashMap<>();
        rules.put('F', new Forward(10));
        rules.put('+', new Turn(90));
        rules.put('-', new Turn(-90));

        String word = kochCurve.iter(this.iterations);

        System.out.println("Koch Curve");
        System.out.println(word);
        System.out.println();

        Program.run(word, rules, this.interpreter);
    }
}
