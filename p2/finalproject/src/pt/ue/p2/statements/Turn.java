package pt.ue.p2.statements;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;

public class Turn extends TurtleStatement {

    private double degree;

    public Turn(double degree) {
        this.degree = degree;
    }

    public double getAngle() {
        return this.degree;
    }

    public void setAngle(double degree) {
        this.degree = degree;
    }

    @Override
    public void run(Interpreter interpreter) {
        interpreter.runTurn(this);
    }
}
