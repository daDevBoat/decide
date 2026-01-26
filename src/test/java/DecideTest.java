import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DecideTest {
    
    @Test
    public void testMatrix() {
        Matrix m = new Matrix(5, 5);
        System.out.println(m);
        m.updateElement(2, 2, Cond.NOTUSED);
        m.updateElement(1, 1, Cond.ORR);
        System.out.println(m);
        System.out.println("fetched (2, 2):" + m.getElement(2, 2));
    }

}
