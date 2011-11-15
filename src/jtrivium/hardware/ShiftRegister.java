package jtrivium.hardware;

/**
 * The interface outlines the basic structure of a shift register.
 * <br>
 * <pre>
 * Copyright (C) 2011 Daniel Ward dwa012@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * </pre>
 * 
 * @author Daniel Ward - dwa012@gmail.com
 * @date October 30, 2011
 * @version 0.1
 */
public interface ShiftRegister {

    /**
     * Will return the size, number of "bits", of this ShiftRegister.
     * 
     * @return The size, number of "bits", of this ShiftRegister.
     */
    public int size();

    /**
     * Get the output bit(s) of this shift register.
     * <br>
     * All implementing classes must indicate many bits will be returned.
     * 
     * @ensure The returned output will be a byte representing one bit,<br>
     *         or a byte representing up to 8 bits. This will be determined<br>
     *         by the implementing class.
     */
    public byte getOuput();

    /**
     * Get a bit form the register from the given position.
     * 
     * @require position >= 0 && position < this.size()
     * @ensure Will return the bit at the given position as a byte.<br>
     *         result == 0x00 || result = 0x01
     * @param position The position of the bit in this ShiftRegister
     * @return The bit at the given position.
     */
    public byte getBitAt(int position);

    /**
     * Load a value into the register. The register loads values from the left.<br>
     * <br>
     * All implementing classes must indicate how many bits can be loaded at one time.
     * 
     * @param value The value to be loaded into the register.
     */
    public void loadValue(byte value);

    /**
     * Get the bits from this ShiftRegister at the given positions.<br>
     * The returned array will contain the the bits in the same order that<br>
     * the positions were give.<br>
     * <br>
     * 
     * @param positions The positions of the bits to be returned
     */
    public byte[] getBits(int... positions);
}
