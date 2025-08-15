# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

ProgrammedCircuitCard is an addon mod for GregTech and AE2. It's a port of Neeve's AE2 Programmed Circuit Card to version 1.20.1, adding an upgrade card that can be inserted into Pattern Providers to automatically set GT machine circuit numbers when executing recipes requiring programmed circuits.

## Development Commands

### Build & Test
```bash
./gradlew build               # Build the project
./gradlew jar                # Create JAR file
./gradlew clean              # Clean build artifacts
./gradlew runClient          # Launch Minecraft client in dev environment
./gradlew runServer          # Launch Minecraft server in dev environment
./gradlew genEclipseRuns     # Generate Eclipse run configurations
./gradlew genIntellijRuns    # Generate IntelliJ run configurations
```

### Guide-related
```bash
./gradlew genGuideResources  # Generate guide resource pack to build/guides
./gradlew copyResourcePacks  # Copy build/guides to run/resourcepacks (depends on genGuideResources)
```

## Code Architecture

### Package Structure
- `yuuki1293.pccard` - Main mod class and core functionality
- `yuuki1293.pccard.mixins` - Mixin injections for AE2 and addon mods
- `yuuki1293.pccard.wrapper` - Interface definitions for mixins
- `yuuki1293.pccard.impl` - PatternProviderLogic implementations
- `yuuki1293.pccard.xmod` - Cross-mod compatibility fixes

### Key Classes
- **PCCard.java**: Main mod class handling item registration, upgrade compatibility, and pattern provider integrations
- **ConfigClient.java**: Client-side configuration (JEI integration toggle, etc.)
- **NBTs.java**: NBT tag constants
- **TagUtils.java**: NBT tag manipulation utilities

### Mixin Strategy
The mod primarily uses Mixins to extend AE2 and various addon mod functionalities:

#### Supported Pattern Providers
- **AE2**: PatternProviderLogic, PatternProviderMenu, PatternProviderScreen
- **ExtendedAE**: Ex Pattern Provider Block & Part
- **AdvancedAE**: Adv Pattern Provider & Small Adv Pattern Provider
- **ExpandedAE**: Exp Pattern Provider Block & Part
- **MegaCells**: Mega Pattern Provider Block & Cable Part

#### Key Mixin Points
- Auto-insert programmed circuits during pattern processing
- Preserve circuit information during crafting CPU execution
- Integrate circuit information in pattern detail displays
- Auto-place circuits in JEI/EMI integration

### Resources
- **Language files**: `src/main/resources/assets/pccard/lang/` (en_us.json, ja_jp.json)
- **Textures**: `src/main/resources/assets/pccard/textures/item/card_programmed_circuit.png`
- **Models**: `src/main/resources/assets/pccard/models/item/card_programmed_circuit.json`
- **Recipes**: `src/main/resources/data/pccard/recipes/item/card_programmed_circuit.json`
- **Guides**: `guidebook/` directory (English & Japanese support)

## Version Management & Releases

### Release Process (from README.md)
1. Commit with "update version to vx.y.z"
2. Add tag in format "v1.2x.x-x.y.z"
3. Push with tag

### Version Format
- `minecraft_version-mod_version` (e.g., 1.20.1-1.0.16)
- Managed in gradle.properties
- CHANGELOG follows Keep a Changelog format

## Development Notes

### Mixin Debugging
- `mixin.debug.export = true` is configured
- Set log level to debug during development to monitor mixin behavior

### Guide Updates
To update guide resource pack during development:
1. Run `gradlew genGuideResources copyResourcePacks`
2. Use F3+T in-game to reload resources

### Supported Mod Versions
Dependency mod versions are managed in gradle.properties. Compatibility testing required when updating to new versions.