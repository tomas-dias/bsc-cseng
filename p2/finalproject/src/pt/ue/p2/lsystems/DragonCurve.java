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
import pt.ue.p2.statements.Turn;

public class DragonCurve implements LSystemExample {
    
    private int iterations;
    private Interpreter interpreter;

    public DragonCurve(int iterations, Interpreter interpreter) {
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

        LSystem dragonCurve = new LSystemImpl("+-");
        dragonCurve.setStart("F");
        dragonCurve.addRule('F', "F+G");
        dragonCurve.addRule('G', "F-G");

        Map<Character, TurtleStatement> rules = new HashMap<>();
        rules.put('F', new Forward(10));
        rules.put('G', new Forward(10));
        rules.put('+', new Turn(90));
        rules.put('-', new Turn(-90));

        String word = dragonCurve.iter(this.iterations);

        System.out.println("Dragon Curve");
        System.out.println(word);
        System.out.println();

        Program.run(word, rules, this.interpreter);
    }
}
