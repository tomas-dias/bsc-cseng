package pt.ue.p2.statements;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;

public class Leap extends TurtleStatement {

    private double distance;

    public Leap(double distance) {
        this.distance = distance;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public void run(Interpreter interpreter) {
        interpreter.runLeap(this);
    }
}
