package pizzamaker.Products;

import com.pizzamaker.Products.Tomato;
import com.pizzamaker.Products.TomatoSauce;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class TomatoSauceTest {
    @Test
    public void addToTomatoSauce_addSingleTomato() {
        TomatoSauce tomatoSauce = new TomatoSauce();
        Assertions.assertEquals(0, tomatoSauce.mass());

        Tomato tomato = Mockito.mock(Tomato.class);
        Mockito.when(tomato.mass()).thenReturn(100);

        tomatoSauce.addTomatoToSauce(tomato);
        Assertions.assertEquals(100, tomatoSauce.mass());
        Assertions.assertEquals(1, tomatoSauce.getTotalTomatoesCount());

        Mockito.verify(tomato).mass();
        Mockito.verify(tomato).cook(100 / 5);
    }

    @Test
    public void addToTomatoSauce_addMultipleTomatoes() {
        TomatoSauce tomatoSauce = new TomatoSauce();
        final Random rnd = new Random();

        int totalMass = 0;
        int totalCount = 50;

        for (int i = 0; i < totalCount; i++) {
            int mass = rnd.nextInt(500);
            Tomato tomato = Mockito.mock(Tomato.class);
            Mockito.when(tomato.mass()).thenReturn(mass);

            tomatoSauce.addTomatoToSauce(tomato);
            totalMass += mass;

            Mockito.verify(tomato).mass();
            Mockito.verify(tomato).cook(mass / 5);
        }

        Assertions.assertEquals(totalCount, tomatoSauce.getTotalTomatoesCount());
        Assertions.assertEquals(totalMass, tomatoSauce.mass());
    }
}
