package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

/** Variant 83447690: types, base stats and assigned moves. */
public final class Steelix extends Pokemon {
    public Steelix(String name, int level) {
        super(name, level);
        setType(Type.STEEL, Type.GROUND);
        setStats(75, 85, 200, 55, 65, 30);
        setMove(new Punishment(), new DreamEater(), new Flamethrower());
    }
}

