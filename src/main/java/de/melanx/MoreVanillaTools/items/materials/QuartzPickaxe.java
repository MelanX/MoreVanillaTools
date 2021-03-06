package de.melanx.MoreVanillaTools.items.materials;

import de.melanx.MoreVanillaTools.items.base.PickaxeBase;
import de.melanx.morevanillalib.LibConfigHandler;
import de.melanx.morevanillalib.api.ToolMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class QuartzPickaxe extends PickaxeBase {

    private static final int DAMAGE = 0;
    private static final int SPEED = -3;

    public QuartzPickaxe() {
        super(ToolMaterials.QUARTZ, DAMAGE, SPEED);
    }

    @Override
    public boolean onBlockDestroyed(@Nonnull ItemStack stack, @Nonnull World worldIn, @Nonnull BlockState state, @Nonnull BlockPos pos, @Nonnull LivingEntity entityLiving) {
        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) < 1) {
            Block block = state.getBlock();
            if (block == Blocks.NETHER_QUARTZ_ORE) {
                ItemStack drop = new ItemStack(Items.QUARTZ);
                double chance = LibConfigHandler.quartzDoubleDropChance.get();
                if (worldIn.rand.nextDouble() < chance && LibConfigHandler.quartzDoubleDrop.get())
                    worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), drop));
            }
        }
        return super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving);
    }
}
