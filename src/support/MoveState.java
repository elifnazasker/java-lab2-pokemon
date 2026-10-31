package support;

import java.util.Map;
import java.util.WeakHashMap;
import ru.ifmo.se.pokemon.Pokemon;

/** State is per Pokemon, never per shared move class. Use fresh teams per battle. */
public final class MoveState {
    private static final Map<Pokemon, Boolean> ACTED = new WeakHashMap<>();
    private static final Map<Pokemon, Boolean> GROUNDED = new WeakHashMap<>();
    private MoveState() { }
    /** True only on the first attempted custom action; see documented engine limits. */
    public static boolean beginAction(Pokemon user) { return ACTED.put(user, true) == null; }
    public static void ground(Pokemon target) { GROUNDED.put(target, true); }
    public static boolean isGrounded(Pokemon target) { return GROUNDED.containsKey(target); }
}
