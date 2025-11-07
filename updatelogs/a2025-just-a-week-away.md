# == Frontiers a2025.2.0: Just a Week Away ==

(IF YOU SEE THIS ON GITHUB THIS IS PROBABLY BEING MERGED WITH 2026.0.1)

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
### Slime Trails
- Naturally spawns in slime chunks
- Slime Trail blocks have a very rare chance to drope a single Slime Ball when broken, or can be mined with Silk Touch
### Hardened Slime
### Slime Shoes
### Bouncy Ball
- A unique variation of Ball, craftable from 4 Slime Balls and 1 uncolored Ball
- Can bounce up to 4 times off blocks
- If hit with anything that would deflect a ball, its bounce count replenishes - allowing you to do even cooler things!
- (The bounce code is based on code from @Yirmiri - go check out Dungeon's Delight if you haven't yet!)

--------------------------
## Changes
### Bouncy Arrow
- Will now ricochet off of enemies when hitting them
  - This also will prevent the arrow from showing up stuck in a player when hit
  - Will consequently no longer work with the Piercing Enchantment 
- Will now bounce 2 times instead of 1
- Rewrote ricochet code (based on code from @Yirmiri, thank you so much!)
  - Will now bounce off a wall more realistically, allowing for far greater control of the arrow
### Dynamite Arrow
- Will now cause a fiery explosion if the arrow is set on fire
### Ball (Entity)
- Now has new NBT values that determine how bouncy it is - `DefaultBounces` & `BouncesLeft`
### Personal Chest
- When attempting to break a Personal Chest when you don't own it, it will now turn translucent and disable collision
- Personal Chests are no longer indestrucible, but will instead take 500 seconds to mine by players who don't own them
### Eboncork
- Added Eboncork Doors & Trapdoors
### Blighted Birch
- Added Blighted Birch Doors & Trapdoors
### Chicken
- Changed Golden Egg drop chance from 50% to 25% to account for Golden Chickens
### Necro Weave
- Updated texture

--------------------------
## Fixes
- All Frontiers blocks that should be flammable (i.e Blighted Birch) are now flammable
- Fixed Spirit Candles not being able to be lit by flaming projectiles