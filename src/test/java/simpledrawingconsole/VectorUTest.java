package simpledrawingconsole;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VectorUTest {

    @Test
    public void addsVectorsAsExpected() {
        Vector vector1 = new Vector(1, 23);
        Vector vector2 = new Vector(8, -4);

        assertEquals(new Vector(9, 19), vector1.add(vector2));
    }

    @Test
    public void subtractsVectorsAsExpected() {
        Vector vector1 = new Vector(31, 49);
        Vector vector2 = new Vector(69, -10);

        assertEquals(new Vector(-38, 59), vector1.subtract(vector2));
    }

    @Test
    public void simplifiesPositiveVectorsAsExpected() {
        Vector vector = new Vector(6, 27);

        assertEquals(new Vector(2, 9), vector.simplify());
    }

    @Test
    public void simplifiesNegativeVectorsAsExpected() {
        Vector vector = new Vector(210, -450);

        assertEquals(new Vector(7, -15), vector.simplify());
    }

    @Test
    public void simplifiesZeroVectorAsExpected() {
        Vector vector = new Vector(0, 0);

        assertEquals(new Vector(0, 0), vector.simplify());
    }
}
