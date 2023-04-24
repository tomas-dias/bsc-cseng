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

public class SierpinskiTriangle implements LSystemExample {
    
    private int iterations;
    private Interpreter interpreter;

    public SierpinskiTriangle(int iterations, Interpreter interpreter) {
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
        
        LSystem sierpinskiTriangle = new LSystemImpl("+-");
        sierpinskiTriangle.setStart("F-G-G");
        sierpinskiTriangle.addRule('F', "F-G+F+G-F");
        sierpinskiTriangle.addRule('G', "GG");

        Map<Character, TurtleStatement> rules = new HashMap<>();
        rules.put('F', new Forward(10));
        rules.put('G', new Forward(10));
        rules.put('+', new Turn(120));
        rules.put('-', new Turn(-120));

        String word = sierpinskiTriangle.iter(this.iterations);

        System.out.println("Sierpinski Triangle");
        System.out.println(word);
        System.out.println();

        Program.run(word, rules, this.interpreter);
    }
}
