package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

/** Variant 83447690: types, base stats and assigned moves. */
public final class Flareon extends Pokemon {
    public Flareon(String name, int level) {
        super(name, level);
        setType(Type.FIRE);
        setStats(65, 130, 60, 95, 110, 65);
        setMove(new FakeOut(), new Swagger(), new Withdraw());
    }
}

