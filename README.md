# Programmed Circuit Card
This mod is an add-on for GregTech and AE2.  
Port Neeve's AE2 Programmed Circuit Card to 1.20.1.

## Description
Programmed Circuit Card can be inserted into Pattern Provider. When pushing a recipe that requires a Programmed Circuit, set the GT machine number.  

## Images
![](https://github.com/user-attachments/assets/c16816e1-2d03-453a-abd0-14a539bded3d)
![](https://github.com/user-attachments/assets/a6e7a248-fc94-4c8b-a595-5bde17a148e7)
![](https://github.com/user-attachments/assets/fa83040f-3244-481b-a941-c63aedd7f713)

## Credits
- [GregTechCEu Modern](https://www.curseforge.com/minecraft/mc-mods/gregtechceu-modern) (KilaBash)
- [Applied-Energistics-2](https://www.curseforge.com/minecraft/mc-mods/applied-energistics-2) (thetechnici4n)
- [Neeve's AE2: Extended Life Additions](https://www.curseforge.com/minecraft/mc-mods/nae2) (notmywing)

## Supported Addons
- [ExtendedAE](https://www.curseforge.com/minecraft/mc-mods/ex-pattern-provider) (GlodBlock)
- [AdvancedAE](https://www.curseforge.com/minecraft/mc-mods/advancedae) (pedroksl)
- [MAE2](https://www.curseforge.com/minecraft/mc-mods/modern-ae2-additions) (AE2Enthusiast)

## Develop
By executing these tasks, the guide's resource pack can be updated during running.
### Generate resource pack for guide.
generate to [build/guides](build/guides).
```groovy
gradlew genGuideResources
```
### Copy resource packs to run
copy [build/guides](build/guides) to [run/resourcepacks](run/resourcepacks).  
depends on `genGuideResources`
```groovy
gradlew copyResourcePacks
```
