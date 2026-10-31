# Assigned move catalogue — 83447690

All assignments match the supplied screenshots. Modern standard move values are used; the screenshots do not specify a generation.

| Class | Base class | Type | Power | Accuracy | Behavior |
| --- | --- | --- | --- | --- | --- |
| Overheat | SpecialMove | FIRE | 130 | 90% | Self Sp.Atk −2 |
| Rest | StatusMove | PSYCHIC | — | Self | Full HP; replace major status with two subsequent turns of sleep |
| KarateChop | PhysicalMove | FIGHTING | 50 | 100% | Critical chance 1/8; engine critical damage ×2 |
| ConfuseRay | StatusMove | GHOST | — | 100% | Confusion via library |
| Punishment | PhysicalMove | DARK | 60–200 | 100% | 60 + 20 per positive target stage, capped at 200 |
| DreamEater | SpecialMove | PSYCHIC | 100 | 100% | Sleeping targets only; heal half actual HP drained, capped at max HP |
| Flamethrower | SpecialMove | FIRE | 90 | 100% | 10% burn |
| SmackDown | PhysicalMove | ROCK | 50 | 100% | Records grounded marker; airborne mechanics unsupported by engine |
| FakeOut | PhysicalMove | NORMAL | 40 | 100% | Priority +3; first attempted action only; cancels prepared target move |
| Swagger | StatusMove | NORMAL | — | 85% | Target Attack +2 and confusion |
| Withdraw | StatusMove | WATER | — | Self | Self Defense +1 |
| ViceGrip | PhysicalMove | NORMAL | 55 | 100% | Damage only; assignment spelling retained |

Accuracy is passed to this JAR as a fraction (0.9, not 90).
Rest counts the current end-of-turn tick plus two future sleeping turns: turns(3).
Self-targeting Rest and Withdraw do not check opponent evasion.
Dream Eater rounds healing and limits it to missing HP; overkill is excluded from drained HP.
The engine allows fractional maximum HP and integer damage; the last damage point can leave a fainted target slightly below zero HP.
Punishment recalculates power for every hit and counts positive stored stages of all seven non-HP stats, ignoring negative stages.

## Engine-specific limits

- Fake Out cancels the already prepared target move through LibraryAccess, so it prevents the current action and does not suppress the following turn.
- Its first-turn rule is represented by the first attempted action of the user. Swagger, Withdraw and ViceGrip register attempts too. Confusion self-hits or skipped turns before the first custom action are not counted. Use fresh objects for each battle; voluntary switching is not implemented.
- Smack Down records a grounding marker. It does not remove Flying typing, which would incorrectly change other type interactions. The JAR has no airborne or ability API; no assigned Ground move consumes the marker.
- Confusion, burn, generic accuracy, damage and other move critical hits follow the engine. Karate Chop uses modern 1/8 probability with the engine's ×2 critical damage; the engine uses a speed-based critical formula for other moves.
- The library's type-immunity checks apply to status moves too. Confuse Ray against a hypothetical Normal-type target would incorrectly fail; none of the six assigned Pokémon is Normal-type. General Ghost-type interactions for Swagger are likewise outside this lineup.
- Negative stat stages suffer from the supplied JAR's calculation defect, including Overheat's drop and burn. No library bytecode was modified.
- PP, abilities, items, weather and airborne move cancellation are not implemented.

## Move references

- [Overheat](https://pokemondb.net/move/overheat)
- [Rest](https://pokemondb.net/move/rest)
- [KarateChop](https://pokemondb.net/move/karate-chop)
- [ConfuseRay](https://pokemondb.net/move/confuse-ray)
- [Punishment](https://pokemondb.net/move/punishment)
- [DreamEater](https://pokemondb.net/move/dream-eater)
- [Flamethrower](https://pokemondb.net/move/flamethrower)
- [SmackDown](https://pokemondb.net/move/smack-down)
- [FakeOut](https://pokemondb.net/move/fake-out)
- [Swagger](https://pokemondb.net/move/swagger)
- [Withdraw](https://pokemondb.net/move/withdraw)
- [ViceGrip](https://pokemondb.net/move/vise-grip)

The screenshot uses “Vice Grip”; the modern reference spells it “Vise Grip”. The Java class preserves the assignment's spelling.

