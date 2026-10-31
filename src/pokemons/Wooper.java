package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

/** Variant 83447690: types, base stats and assigned moves. */
@SuppressWarnings("this-escape")
public class Wooper extends Pokemon {
    public Wooper(String name, int level) {
        super(name, level);
        setType(Type.WATER, Type.GROUND);
        setStats(55, 45, 45, 25, 25, 15);
        setMove(new FakeOut(), new Swagger());
    }
}

