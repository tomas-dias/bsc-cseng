package pt.ue.p2.statements;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.interfaces.Interpreter;

public class Forward extends TurtleStatement {

    private double distance;

    public Forward(double distance) { 
        this.distance = distance; 
    }

    public double getDistance() { 
        return distance; 
    }

    public void setDistance(double distance) { 
        this.distance = distance; 
    }

    @Override
    public void run(Interpreter interpreter) {
        interpreter.runForward(this);
    }
}
