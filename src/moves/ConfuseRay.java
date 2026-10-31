package moves;

import ru.ifmo.se.pokemon.*;

public final class ConfuseRay extends StatusMove {
    public ConfuseRay() { super(Type.GHOST, 0, 1); }
    // The engine's status type gate incorrectly blocks Normal targets;
    // none of the six assigned species has that type (see MOVES.md).
    @Override protected void applySelfEffects(Pokemon self) { }
    @Override protected void applyOppEffects(Pokemon target) { Effect.confuse(target); }
    @Override protected String describe() { return "uses Confuse Ray"; }
}
