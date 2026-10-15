## [0.0.4]
This version is a massive restructuring, that began with a complete wipe of the project shortly after the release of v0.0.3.

### Additions
- Added TagPrefixes:
  - TFC and GT Tool heads
  - TFC stone types and sand colors (for Ores)
  - TFC metal items, including oxidizables
  - Small, Poor, and Rich ore items (a la TFC)
  - Powders: A fine dust
  - Aspectus
- Added TFC stone types to GT COBBLE_BLOCKS map for compat with the Miner.
- Added MaterialIconTypes:
  - TFC and GT tool heads
  - Poor and Rich ore items
- Added MaterialIconSets:
  - Hallowed, Soulstained, Malignant
  - Botryoidal, Cubic, Octahedral, Tabular (for ore blocks)
- Added TFCProperty for handling GT Materials that interact with TFC systems, namely melting and forging.
- Added ToolBehaviors and ToolTypes for TFC's Chisel and Mace
- Added additional tiers to GT Electrical Tools, up to IV
- Added Elements
  - Ember
- Added Materials
  - Mod Integration: 
    - Pewter (Eidolon: Repraised)
    - Dawnstone, Ember (Embers)
    - Fluix (AE2)
    - Weak Steels, Pig Iron, Kaolinite (TFC)
    - Hallowed Gold, Malignant Pewter, Soulstained Steel (Malum)
  - Ore Materials: 
    - Acanthite (Silver Sulfide)
    - Arsenopyrite (Iron-Arsenic Sulfide)
    - Bismuthinite (Bismuth Sulfide)
    - Columbite (Niobium Oxide)
    - Cryolite (for Hall-Héroult process)
    - Fluorite (Calcium Fluoride)
    - Hafnon (Hafnium Silicate)
    - Millerite (Nickel Sulfide)
    - Petalite (Lithium-Aluminum Silicate)
    - Sperrylite (Platinum Arsenide)
    - Thorianite (Thorium Oxide)
    - Thorite (Thorium Silicate)
    - Wolframite (Tungsten Oxide)
    - Zircon (Zirconium Silicate)
    - Anthracite (Dense, High-Grade Coal)
    - Lignite (Low-Grade Coal)
  - Compound Materials:
    - Alumina (Aluminum Oxide)
    - Beryllia (Beryllium Oxide)
    - Chromium Oxide
    - Hydrogen Chloride, Hydrogen Fluoride (Gases)
  - Rock Materials: (defined but not yet implemented)
    - FelsicIgneous
    - IntermediateIgneous
    - MaficIgneous
    - ClasticSedimentary
    - CarbonateSedimentary
    - SiliceousSedimentary
    - Metamorphic
- Added MaterialFlags:
  - GENERATE_DOUBLE_INGOT: To generate double ingot items, primarily used by TFC
  - TFC_CASTABLE: For materials that can be cast into tool heads using TFC molds
  - HAS_TFC_OXIDIZATION: For oxidizable TFC materials
  - HAS_TFC_ARMOR: For TFC materials with armor (KubeJS recipe generation)
  - Various flags for generating GT tool heads for materials without requiring the material to have that tool type.
- Added Multiblocks (WIP)
  - Electrolytic Cell: A large electrolyzer for complex electrolysis recipes (for later electrolysis overhaul)
  - Spiral Separator: A bulk ore washer that only costs water (for later oreproc overhaul)
  - Metallurgical Convertor: For bulk processing of molten pig iron and copper mattes into Steel and Blister Copper
  - Flash Furnace: For producing nonferrous metals from sulfide ores, uses distillation tower logic for dynamic sulfur recovery
- Added Machines
  - Roaster: For roasting ores
- Added Bismuth Bronze and Black Bronze Drums and Crates
- Added ULV Super Tank and ULV Super Chest (these may be replaced by dynamic multiblock containers)
- Added Components and Covers
  - ULV-Tier Steam Motor, Piston, Conveyor, and Pump for early game item and fluid transfer
  - Air Vent Cover: Gradually imports air into the attached machine
- Added Blocks:
  - Dwarven Machine Casing, Lead Machine Casing, Lead Pipe Casing, Bricked Lead Casing
  - High Pressure Steam Machine Casing
  - Stainless Steel Pipe Casing
  - Anthracite: Used as an ore block, like TFC's Lignite or Bituminous Coal
- Added Laterite Blocks: Bauxite, Garnierite, Limonite (WIP)
  - These are dirt and grass-like deposit blocks, they function similarly to TFC's gravel deposits.
- Added Ore Vein Info EMI Category (Credits to TerraFirmaGreg!)

### Changes
- Changed mod id to 'tfinfinity'
- Many GT material modifications
  - Added ingots and BlastProperty to Hafnium, Zirconium
  - Remove BlastProperty from low-tier TFC alloys (bronzes, etc.)
  - Lowered BlastTemperature for TFC colored steels to Cupronickel range, and adjusted fluid temperatures accordingly
  - Applied SetIgnored for pre-existing material items
  - Removed Ore Property from many Native Metals
    - Aluminium, Beryllium, Cobalt, Iron, Lead, Lithium, Molybdenum, Neodymium, Nickel, Plutonium-239, Thorium, Tin
  - Removed Ore Property from unused Ores
    - Electrotine, Soapstone, Tungstate
    - Coal: Coal is coal, it occurs as a single ore block supplied by TFC
  - Added Ore Property to Borax, missing garnets (Andradite and Uvarovite)
  - Carbon, Graphite: Added rods
  - Wrought Iron: Macerates into Iron, Removed Fluid Property
  - Annealed Copper: Macerates into Copper, Removed Fluid Property
  - Salt: Added Molten Fluid
  - Mercury: Added Gas/Vapor (for Mercury Vapor Turbines, yet to be implemented)
  - Modified Ore Byproducts
    - Molybdenum to Molybdenite
    - Chromium to Chromite
    - Thorium to Thorianite/Thorite
    - Niobium to Columbite
    - Tantalum to Tantalite
  - Modified Material Components of Alunite, Sodalite, Lazurite, and Lepidolite for improved accuracy 
  - Modified Material Components of Corundum (Ruby, Sapphire) to reference new material Alumina
- Adjusted IO amounts for Arc Furnace and Electrolyzer recipes
- Reduced Quantum Tank, Quantum Chest default capacities
- Remove secondary material info from Wrench and Wirecutter tool heads
- Disabled Ore Block recipes as they clutter recipe viewers and the blocks are unobtainable

### Misc
- Added python scripts for generating ore json files from built in dicts, or by importing from CSV.

## [0.0.3]
### Changes
- Setup Elements, Materials, MaterialFlags, MaterialIconType, and TagPrefix classes
- Added Elements and Materials for certain items included in Dependencies
- Setup Lang Datagen

### New Dependencies
#### Content Mods
- Applied Energistics 2
- Create
- Eidolon : Repraised
- Embers Re-Ignited
- Malum
- ArborFirmaCraft
- Beneath
- Firmalife
- KubeJS

#### Libraries
- Curios (Malum, Eidolon : Repraised, Embers Re-Ignited)
- GuideME (AE2)
- Ponder, Flywheel (Create)
- Lodestone (Malum)
- Rhino (KubeJS)

## [0.0.2]
### Changes
- Added dependencies
  - GTM + deps
- Gradle refactor
- Packages refactor

## [0.0.1]
### Changes
- initial release