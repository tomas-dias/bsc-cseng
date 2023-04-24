package pt.ue.p2.statements;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;

public class PenDown extends TurtleStatement {

    public PenDown() {

    }
    
    @Override
    public void run(Interpreter interpreter) {
        interpreter.runPenDown(this);
    }
}
