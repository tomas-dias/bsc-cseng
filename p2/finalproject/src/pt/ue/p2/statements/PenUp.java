package pt.ue.p2.statements;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;

public class PenUp extends TurtleStatement {

    public PenUp() {

    }
    
    @Override
    public void run(Interpreter interpreter) {
        interpreter.runPenUp(this);
    }
}
