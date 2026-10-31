package moves;

import ru.ifmo.se.pokemon.*;
import support.MoveState;

public final class SmackDown extends PhysicalMove {
    public SmackDown() { super(Type.ROCK, 50, 1); }
    @Override protected void applyOppEffects(Pokemon target) {
        // The JAR has no airborne/ability API. Keep a grounding marker without
        // deleting Flying typing (which would incorrectly alter STAB and weaknesses).
        MoveState.ground(target);
    }
    @Override protected String describe() { return "uses Smack Down"; }
}
