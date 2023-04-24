package pt.ue.p2.statements;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;

public class Restore extends TurtleStatement {

    public Restore() {

    }
    
    @Override
    public void run(Interpreter interpreter) {
        interpreter.runRestore(this);
    }
}
