package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

/** Variant 83447690: types, base stats and assigned moves. */
public final class Quagsire extends Wooper {
    public Quagsire(String name, int level) {
        super(name, level);
        setType(Type.WATER, Type.GROUND);
        setStats(95, 85, 85, 65, 65, 35);
        addMove(new Withdraw());
        addMove(new ViceGrip());
    }
}

