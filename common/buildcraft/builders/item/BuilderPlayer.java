/* Copyright (c) 2016 AlexIIL and the BuildCraft team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. */
package buildcraft.builders.item;

import com.google.common.collect.ImmutableSet;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import net.minecraftforge.fluids.FluidStack;

import buildcraft.api.bpt.BptPermissions;
import buildcraft.api.bpt.IBptAction;
import buildcraft.api.bpt.IBuilderAccessor;
import buildcraft.api.core.IFluidFilter;
import buildcraft.api.core.IStackFilter;
import buildcraft.lib.permission.PlayerOwner;

public class BuilderPlayer implements IBuilderAccessor {
    private static final ImmutableSet<BptPermissions> PERMISSIONS_CREATIVE, PERMISSIONS_SURVIVAL;

    static {
        PERMISSIONS_CREATIVE = ImmutableSet.of(BptPermissions.USE_ITEMS, BptPermissions.FREE_MATERIALS);
        PERMISSIONS_SURVIVAL = ImmutableSet.of(BptPermissions.USE_ITEMS);
    }

    public final EntityPlayer player;
    private final PlayerOwner owner;

    public BuilderPlayer(EntityPlayer player) {
        this.player = player;
        this.owner = PlayerOwner.getOwnerOf(player);
    }

    @Override
    public World getWorld() {
        return player.worldObj;
    }

    @Override
    public Vec3d getBuilderPosition() {
        return player.getPositionVector();
    }

    @Override
    public PlayerOwner getOwner() {
        return owner;
    }

    @Override
    public ImmutableSet<BptPermissions> getPermissions() {
        if (player.capabilities.isCreativeMode) {
            return PERMISSIONS_CREATIVE;
        } else {
            return PERMISSIONS_SURVIVAL;
        }
    }

    @Override
    public int startBlockAnimation(Vec3d target, IBlockState state, int delay) {
        return 0;
    }

    @Override
    public int startItemStackAnimation(Vec3d target, ItemStack display, int delay) {
        return 0;
    }

    @Override
    public int[] startFluidAnimation(Vec3d target, FluidStack fluid, int delay) {
        return new int[] { 0, 0 };
    }

    @Override
    public int[] startPowerAnimation(Vec3d target, int milliJoules, int delay) {
        return new int[] { 0, 0 };
    }

    @Override
    public IRequestedStack requestStack(IStackFilter filter, int amunt) {
        return new IRequestedStack() {
            @Override
            public void release() {
                throw new AbstractMethodError("Implement this!");
            }

            @Override
            public ItemStack getRequested() {
                throw new AbstractMethodError("Implement this!");
            }
        };
    }

    @Override
    public IRequestedFluid requestFluid(IFluidFilter filter, int amount) {
        return new IRequestedFluid() {
            @Override
            public void release() {
                throw new AbstractMethodError("Implement this!");
            }

            @Override
            public FluidStack getRequested() {
                throw new AbstractMethodError("Implement this!");
            }
        };
    }

    @Override
    public void addAction(IBptAction action, int delay) {
        action.run(this);
    }
}