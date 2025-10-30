# == Frontiers a2025.2.0: Just the Facts ==

--------------------------
## Additions
### Fruitcake
- Crafted with fruit, milk, sugar, wheat, and eggs
- Unlike regular Cake, using it picks up a slice
- A single slice restores 6 hunger points
- When you can't eat anymore, you can throw it, dealing as much damage as it restores hunger
### Wreaths
- A new decorative block crafted from leaves & assorted materials
- Can be placed on the sides of solid blocks, leaves, and door faces
  - Will not have any collision on doors, and won't break when opening them
- Can be crafted out of every kind of leaves
- Can also be worn on your head. Why not.

--------------------------
## Changes
### Bouncy Arrow
- Will now ricochet off of enemies when hitting them
  - This also will prevent the arrow from showing up stuck in a player when hit
  - Will consequently no longer work with the Piercing Enchantment 
- Will now bounce 2 times instead of 1
- Rewrote ricochet code (based on code from @Yirmiri, thank you so much!)
  - Will now bounce off a wall more realistically, allowing for far greater control of the arrow
### Personal Chest
- When attempting to break a Personal Chest when you don't own it, it will now turn translucent and disable collision
### Eboncork
- Added Eboncork Doors & Trapdoors
### Blighted Birch
- Added Blighted Birch Doors & Trapdoors

--------------------------
## Fixes
- Fixed Spirit Candles not being able to be lit by flaming projectiles