/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 */

package buildcraft.lib.gui.pos;

/** An immutable {@link IGuiArea}. */
public final class GuiRectangle implements IGuiArea {
    /** A rectangle where all of the fields are set to 0. */
    public static final GuiRectangle ZERO = new GuiRectangle(0, 0, 0, 0);

    public final int x, y, width, height;

    public GuiRectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public GuiRectangle(long x, long y, long width, long height) {
        this((int) x, (int) y, (int) width, (int) height);
    }

    public GuiRectangle(int width, int height) {
        this.x = 0;
        this.y = 0;
        this.width = width;
        this.height = height;
    }

    public GuiRectangle(long width, long height) {
        this(0, 0, (int) width, (int) height);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public GuiRectangle asImmutable() {
        return this;
    }

    @Override
    public String toString() {
        return "Rectangle [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
    }

    public GuiRectangle offset(PositionAbsolute by) {
        return offset(by.getX(), by.getY());
    }

    @Override
    public GuiRectangle offset(int dx, int dy) {
        return new GuiRectangle(x + dx, y + dy, width, height);
    }

    @Override
    public GuiRectangle expand(int dX, int dY) {
        return new GuiRectangle(x - dX, y - dY, width + dX * 2, height + dY * 2);
    }
}
