package pt.ue.p2.statements;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;

public class Save extends TurtleStatement {

    public Save() {

    }
    
    @Override
    public void run(Interpreter interpreter) {
        interpreter.runSave(this);
    }
}
