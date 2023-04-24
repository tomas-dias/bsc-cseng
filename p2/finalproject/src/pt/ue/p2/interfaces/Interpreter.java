package pt.ue.p2.interfaces;

import java.util.List;

import pt.ue.p2.core.TurtleStatement;
import pt.ue.p2.statements.Forward;
import pt.ue.p2.statements.Leap;
import pt.ue.p2.statements.PenDown;
import pt.ue.p2.statements.PenUp;
import pt.ue.p2.statements.Restore;
import pt.ue.p2.statements.Save;
import pt.ue.p2.statements.Turn;

public interface Interpreter {
    void run(List<TurtleStatement> program);
    void runForward(Forward statement);
    void runTurn(Turn statement);
    void runPenUp(PenUp statement);
    void runPenDown(PenDown statement);
    void runLeap(Leap statement);
    void runSave(Save statement);
    void runRestore(Restore statement);
}
