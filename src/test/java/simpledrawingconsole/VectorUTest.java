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
}
