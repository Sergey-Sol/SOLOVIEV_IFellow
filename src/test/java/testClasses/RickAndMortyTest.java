package testClasses;

import hooks.WebHooks;
import org.junit.jupiter.api.Test;
import steps.RAMSteps;


public class RickAndMortyTest extends WebHooks {
    @Test
    public void сompareСharactersTest(){
        new RAMSteps()
                .сompareСharacters("https://rickandmortyapi.com/api",200);
    }
}
