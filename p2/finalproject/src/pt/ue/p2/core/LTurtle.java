package pt.ue.p2.core;

import java.awt.*;
import java.util.List;

import galapagos.Turtle;
import galapagos.TurtleDrawingWindow;
import pt.ue.p2.interfaces.Interpreter;
import pt.ue.p2.statements.Forward;
import pt.ue.p2.statements.Leap;
import pt.ue.p2.statements.PenDown;
import pt.ue.p2.statements.PenUp;
import pt.ue.p2.statements.Restore;
import pt.ue.p2.statements.Save;
import pt.ue.p2.statements.Turn;

public class LTurtle implements Interpreter {
    
    private final Turtle turtle;
    private final TurtleDrawingWindow window;

    public LTurtle() {
        this.window = new TurtleDrawingWindow();
        this.turtle = new Turtle(Turtle.NO_DEFAULT_WINDOW);
    }

    public Turtle getTurtle() {
        return this.turtle;
    }

    private void init() {

        this.turtle.show();
        this.turtle.bodyColor(Color.RED);
        this.turtle.penColor(Color.RED);
        this.turtle.speed(1000);

        this.window.add(this.turtle);
        this.window.setGrid(false);
        this.window.setSize(1080, 720);
        this.window.setOrigin(0, 0);
        this.window.setUnit(1);
        this.window.setBackground(Color.WHITE);
        this.window.setVisible(true);
    }

    @Override
    public void run(List<TurtleStatement> program) {
        init();
        program.forEach(statement -> statement.run(this));
    }

    @Override
    public void runForward(Forward statement) {
        this.turtle.penDown();
        this.turtle.move(statement.getDistance());
    }

    @Override
    public void runTurn(Turn statement) {
        this.turtle.turn(statement.getAngle());
    }

    @Override
    public void runPenUp(PenUp statement) {
        this.turtle.penUp();
    }

    @Override
    public void runPenDown(PenDown statement) {
        this.turtle.penDown();
    }

    @Override
    public void runLeap(Leap statement) {
        this.turtle.penUp();
        this.turtle.forward(statement.getDistance());
        this.turtle.penDown();
    }

    @Override
    public void runSave(Save statement) {
    }

    @Override
    public void runRestore(Restore statement) {
    }
}
