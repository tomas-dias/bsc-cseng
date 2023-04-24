import pt.ue.p2.core.LTurtle;
import pt.ue.p2.interfaces.Interpreter;
import pt.ue.p2.lsystems.CantorSet;
import pt.ue.p2.lsystems.DragonCurve;
import pt.ue.p2.lsystems.FractalPlant;
import pt.ue.p2.lsystems.KochCurve;
import pt.ue.p2.lsystems.KochSnowflake;
import pt.ue.p2.lsystems.SierpinskiArrowhead;
import pt.ue.p2.lsystems.SierpinskiTriangle;

public class App {
    public static void main(String[] args) throws Exception {
        
        Interpreter interpreter = new LTurtle();

        /* Koch Curve */
        KochCurve kochCurve = new KochCurve(2, interpreter);
        kochCurve.execute();

        /* Koch Snowflake */
        KochSnowflake kochSnowflake = new KochSnowflake(2, interpreter);
        // kochSnowflake.execute();

        /* Sierpinski Triangle */
        SierpinskiTriangle sierpinskiTriangle = new SierpinskiTriangle(2, interpreter);
        // sierpinskiTriangle.execute();

        /* Sierpinski Arrowhead */
        SierpinskiArrowhead sierpinskiArrowhead = new SierpinskiArrowhead(2, interpreter);
        // sierpinskiArrowhead.execute();

        /* Dragon Curve */
        DragonCurve dragonCurve = new DragonCurve(2, interpreter);
        // dragonCurve.execute();

        /* Cantor Set */
        CantorSet cantorSet = new CantorSet(2, interpreter);
        // cantorSet.execute();

        /* Fractal Plant */
        FractalPlant fractalPlant = new FractalPlant(2, interpreter);
        // fractalPlant.execute();
    }
}
