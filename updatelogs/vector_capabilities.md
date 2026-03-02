Vector has several special capabilities that ease the process of making cross-loader mods, including:

There's also a few special 
- `VectorLootMod`: A loader-independent solution to replacing & modifying existing loot tables - even modded ones!
  - Allows replacement of existing loot tables, or modifying pools in them instead
- `VectorNetSync`: A straightforward solution to adding new data to vanilla entities that syncs across clients 
when they start tracking an entity
- `VectorPropertyReg`: An all-in-one, loader-independent solution for adding item properties for common vanilla
interactions, such as
  - `Fuel`: Furnace fuels (even shows up in recipe viewers!)
  - `Fire`: Fire spreading properties
  - `Compost`: Compost values (even shows up in recipe viewers!)
- `VectorToolActions`: Allows defining actions for many common vanilla tool actions, such as:
  - `Shovel`: Allows defining custom path creation on right click
  - `Hoe`: Allows defining custom farmland creation on right click
  - `Axe`: Allows defining custom strippable creation on right click
- `VectorSplash`: A system that allows you to define your own splash texts, without overriding vanilla ones! There's several
options:
  - Provide a textfile with a `ResourceLocation`
  - (Not recommended but exists anyway) Provide a `String` for a single text
  - Provide your own custom class extending `SplashRenderer`, with custom condition definition! Perfect for things like
  holidays, birthdays, uniquely drawn splashes, etc.

Special thanks to the following for their help:
- Yirmiri (creator of RunicLib)
- Hecco (creator of NexusLib)
- Jaredlll08 (creator of the Multiloader Template that facilitated this job)