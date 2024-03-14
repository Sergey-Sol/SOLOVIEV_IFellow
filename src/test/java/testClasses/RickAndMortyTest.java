package testClasses;

import hooks.WebHooks;
import org.junit.jupiter.api.Test;
import pages.RestSteps;


public class RickAndMortyTest extends WebHooks {
    @Test
    public void сompareСharactersTest(){
        new RestSteps()
                .сompareСharacters("https://rickandmortyapi.com/api",200);
    }
}
