package me.yelby.duckets;

import api.config.BlockConfig;
import api.mod.StarMod;
import api.utils.textures.StarLoaderTexture;
import org.schema.game.common.data.element.ElementInformation;
import org.schema.game.common.data.element.FactoryResource;

import javax.imageio.ImageIO;
import java.io.IOException;

/**
 * Ducket Mod, this mod adds 3 blocks. The three blocks use nothing more then decorative, but are also used for server
 * currency.
 *
 * @Author Yelby
 */

public class Duckets extends StarMod
{
    //Global Variables
    StarLoaderTexture bronzeDucketTexture;
    StarLoaderTexture silverDucketTexture;
    StarLoaderTexture goldDucketTexture;

    public void onEnable()
    {
        //Create Material
        try
        {
            String textureRoot = "me/yelby/duckets/textures/";
            //BufferedImage bump = ImageIO.read(getJarResource(textureRoot + "Ducket256NM.png"));
            bronzeDucketTexture = StarLoaderTexture.newBlockTexture(
                    ImageIO.read(getJarResource(textureRoot + "Ducket256_bronze.png"))/*,bump, 2041*/);
            silverDucketTexture = StarLoaderTexture.newBlockTexture(
                    ImageIO.read(getJarResource(textureRoot + "Ducket256_silver.png"))/*,bump, 2042*/);
            goldDucketTexture = StarLoaderTexture.newBlockTexture(
                    ImageIO.read(getJarResource(textureRoot + "Ducket256_gold.png"))/*,bump, 2043*/);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onBlockConfigLoad(BlockConfig blockConfig)
    {
        //Bronze
        ElementInformation bronzeDucket = createBlock((short) bronzeDucketTexture.getTextureId(), blockID.bronzeBar,
                "Ducket100k", "100,000 grills",
                0.1f, 0.1f,
                true, -1);

        FactoryResource[] customRecipe = new FactoryResource[]
                {
                        new FactoryResource(100000, blockID.metalGrill),
                        new FactoryResource(200000, blockID.crystalComposite)
                };
        BlockConfig.addRecipe(bronzeDucket, factoryID.basicFactory, 5/*Bake Time*/, customRecipe);

        //Silver
        ElementInformation silverDucket = createBlock((short) silverDucketTexture.getTextureId(), blockID.silverBar,
                "Ducket10M", "10,000,000 grills",
                0.1f, 0.1f,
                true, -1);

        customRecipe = new FactoryResource[]
                {
                        new FactoryResource(100, bronzeDucket.id),
                        new FactoryResource(200000, blockID.crystalComposite)
                };
        BlockConfig.addRecipe(silverDucket, factoryID.basicFactory, 5/*Bake Time*/, customRecipe);

        //Gold
        ElementInformation goldDucket = createBlock((short) goldDucketTexture.getTextureId(), blockID.goldBar,
                "Ducket1B", "1,000,000,000 grills",
                0.1f, 0.1f,
                true, -1);

        customRecipe = new FactoryResource[]
                {
                        new FactoryResource(100, silverDucket.id),
                        new FactoryResource(200000, blockID.crystalComposite)
                };
        BlockConfig.addRecipe(goldDucket, factoryID.basicFactory, 5/*Bake Time*/, customRecipe);

        //Add Blocks
        BlockConfig.add(bronzeDucket);
        BlockConfig.add(silverDucket);
        BlockConfig.add(goldDucket);
    }

    //Basic stuff for creating a block
    public ElementInformation createBlock(short textureID,   short iconID,
                                          String blockName,  String blockDescription,
                                          float blockMass,   float blockVolume,
                                          boolean craftOnly, int blockPrice)
    {
        ElementInformation block = BlockConfig.newElement(this, blockName, textureID);
        block.setBuildIconNum(iconID);
        block.description = blockDescription;
        block.mass = blockMass;
        block.volume = blockVolume;
        block.setShoppable(!craftOnly);
        block.setPrice(blockPrice);

        return block;
    }
}

class factoryID
{
    public static final short capsuleRefiner = 1;
    public static final short microAssembler = 2;
    public static final short basicFactory = 3;
    public static final short standardFactory = 4;
    public static final short advancedFactory = 5;
}

class blockID
{
    public static final short bronzeBar = 286;
    public static final short silverBar = 287;
    public static final short goldBar = 288;
    public static final short metalGrill = 442;
    public static final short crystalComposite = 220;
}
