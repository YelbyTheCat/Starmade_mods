package me.yelby.florafactory;

import api.config.BlockConfig;
import api.mod.StarMod;
import api.utils.textures.StarLoaderTexture;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.element.FactoryResource;

import javax.imageio.ImageIO;
import java.io.IOException;

public class FloraFactory extends StarMod
{
    StarLoaderTexture floraFactoryTexture;
    StarLoaderTexture floraFactoryTextureIcon;

    public void onEnable()
    {
        String textureRoot = "me/yelby/florafactory/textures/";
        try
        {
            floraFactoryTexture = StarLoaderTexture.newBlockTexture(
                    ImageIO.read(getJarResource(textureRoot + "florafactory-pl.png")));
            floraFactoryTextureIcon = StarLoaderTexture.newIconTexture(
                    ImageIO.read(getJarResource(textureRoot + "florafactory-pl.png")));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void onBlockConfigLoad(BlockConfig blockConfig)
    {
        ElementInformation floraFactory = BlockConfig.newFactory(this, "FloraFactory", new short[] {(short)floraFactoryTexture.getTextureId()});
        floraFactory.setBuildIconNum(floraFactoryTextureIcon.getTextureId());
        int floraFactoryId = BlockConfig.customFactories.get(floraFactory.id);

        FactoryResource[] customRecipe = new FactoryResource[]{};

        //Earth Flora
        ElementInformation[] earthFloraArray = {
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_FLOWERS_BLUE_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_GRASS_LONG_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_BERRY_BUSH_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_FLOWERS_YELLOW_SPRITE)
        };

        for(int i = 0; i < earthFloraArray.length; i++)
        {
            customRecipe = new FactoryResource[]
                    {
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ICE_ID),
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ROCK_GREEN)
                    };
            BlockConfig.addRecipe(earthFloraArray[i], floraFactoryId, 5/*Bake Time*/, customRecipe);
        }

        //Desert Flora
        ElementInformation[] desertFloraArray = {
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_CACTUS_SMALL_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_FLOWERS_DESERT_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_CACTUS_ARCHED_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_ROCK_SPRITE)
        };

        for(int i = 0; i < desertFloraArray.length; i++)
        {
            customRecipe = new FactoryResource[]
                    {
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ICE_ID),
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ROCK_YELLOW)
                    };
            BlockConfig.addRecipe(desertFloraArray[i], floraFactoryId, 5/*Bake Time*/, customRecipe);
        }

        //Mars Flora
        ElementInformation[] marsFloraArray = {
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_CORAL_RED_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_FUNGAL_GROWTH_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_SHROOM_RED_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_FUNGAL_TRAP_SPRITE)
        };

        for(int i = 0; i < marsFloraArray.length; i++)
        {
            customRecipe = new FactoryResource[]
                    {
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ICE_ID),
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ROCK_RED)
                    };
            BlockConfig.addRecipe(marsFloraArray[i], floraFactoryId, 5/*Bake Time*/, customRecipe);
        }

        //Purple Flora
        ElementInformation[] purpleFloraArray = {
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_FLOWER_FAN_PURPLE_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_GLOW_TRAP_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_WEEDS_PURPLE_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_YHOLE_PURPLE_SPRITE)
        };

        for(int i = 0; i < purpleFloraArray.length; i++)
        {
            customRecipe = new FactoryResource[]
                    {
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ICE_ID),
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ROCK_PURPLE)
                    };
            BlockConfig.addRecipe(purpleFloraArray[i], floraFactoryId, 5/*Bake Time*/, customRecipe);
        }

        //Ice Flora
        ElementInformation[] iceFloraArray = {
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_FAN_FLOWER_ICE_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_ICE_CRAG_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_CORAL_ICE_SPRITE),
                ElementKeyMap.getInfo(ElementKeyMap.TERRAIN_SNOW_BUD_SPRITE)
        };

        for(int i = 0; i < iceFloraArray.length; i++)
        {
            customRecipe = new FactoryResource[]
                    {
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ICE_ID),
                            new FactoryResource(1, ElementKeyMap.TERRAIN_ROCK_BLUE)
                    };
            BlockConfig.addRecipe(iceFloraArray[i], floraFactoryId, 5/*Bake Time*/, customRecipe);
        }

        BlockConfig.add(floraFactory);
    }
}
