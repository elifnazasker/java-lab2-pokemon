# Class diagram — variant 83447690

![Class diagram](class-diagram.svg)

```mermaid
classDiagram
    direction TB
    Pokemon <|-- Claydol
    Pokemon <|-- Steelix
    Pokemon <|-- Raikou
    Pokemon <|-- Wooper
    Pokemon <|-- Flareon
    Wooper <|-- Quagsire
    Move <|-- DamageMove
    DamageMove <|-- PhysicalMove
    DamageMove <|-- SpecialMove
    Move <|-- StatusMove
    SpecialMove <|-- Overheat
    StatusMove <|-- Rest
    PhysicalMove <|-- KarateChop
    StatusMove <|-- ConfuseRay
    PhysicalMove <|-- Punishment
    SpecialMove <|-- DreamEater
    SpecialMove <|-- Flamethrower
    PhysicalMove <|-- SmackDown
    PhysicalMove <|-- FakeOut
    StatusMove <|-- Swagger
    StatusMove <|-- Withdraw
    PhysicalMove <|-- ViceGrip
    Main ..> Battle : creates
    Battle o-- Pokemon : two teams
    Pokemon o-- Move : assigned attacks
    Punishment ..> LibraryAccess : reads stages
    FakeOut ..> LibraryAccess : cancels prepared move
    FakeOut ..> MoveState : first attempted action
    Swagger ..> MoveState
    Withdraw ..> MoveState
    ViceGrip ..> MoveState
    SmackDown ..> MoveState : grounded marker
    Move ..> Effect : effects
```

All project leaf classes are final; only Wooper is extensible because Quagsire inherits it.
Library base classes are unchanged. Leaf annotations are summarized here instead of altering their displayed names.

