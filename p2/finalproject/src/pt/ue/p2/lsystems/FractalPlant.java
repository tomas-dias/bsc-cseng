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
import pt.ue.p2.statements.PenUp;
import pt.ue.p2.statements.Restore;
import pt.ue.p2.statements.Save;
import pt.ue.p2.statements.Turn;

public class FractalPlant implements LSystemExample {
    
    private int iterations;
    private Interpreter interpreter;

    public FractalPlant(int iterations, Interpreter interpreter) {
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

        LSystem fractalPlant = new LSystemImpl("+-[]");
        fractalPlant.setStart("X");
        fractalPlant.addRule('X', "F+[[X]-X]-F[-FX]+X");
        fractalPlant.addRule('F', "FF");

        Map<Character, TurtleStatement> rules = new HashMap<>();
        rules.put('F', new Forward(10));
        rules.put('+', new Turn(25));
        rules.put('-', new Turn(-25));
        rules.put('X', new PenUp());
        rules.put('[', new Save());
        rules.put(']', new Restore());

        String word = fractalPlant.iter(this.iterations);

        System.out.println("Fractal Plant");
        System.out.println(word);
        System.out.println();

        Program.run(word, rules, this.interpreter);
    }
}
