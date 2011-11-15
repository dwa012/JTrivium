/*
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
 */

package jtrivium;

/**
 * The class models a ShiftRegister used by Trivium.<br>
 * See http://www.ecrypt.eu.org/stream/triviumpf.html <br>
 * See http://www.ecrypt.eu.org/stream/p3ciphers/trivium/trivium_p3.pdf <br>
 * 
 * @author Daniel Ward - dwa012@gmail.com
 * @date October 30, 2011
 * @version 0.1
 */
public class TriviumShiftRegister implements ShiftRegister {

    //the array of FlipFlops used for this shift regster
    private FlipFlop[] flipFlops;
    private byte tap;//the tap of this ShiftRegister

    /**
     * Creates a ShiftRegister with the number of "bits" given.
     * 
     * @require sizeOfRegister > 0
     * @ensure this.size() = sizeOfRegister && this.getOutput() == 0x00
     * @param sizeOfRegister The number of "bits" of this ShiftRegister.
     */
    public TriviumShiftRegister(int sizeOfRegister) {
        flipFlops = new FlipFlop[sizeOfRegister];

        //create the rightmost FlipFlop as a FlipFlop without a neighbor
        flipFlops[sizeOfRegister - 1] = new FlipFlop();

        //create the FlipFlops for this ShiftRegister
        for (int i = flipFlops.length - 2; i >= 0; i--) {
            flipFlops[i] = new FlipFlop(flipFlops[i + 1]);
        }

        //initialize the tap
        tap = 0x00;
    }

    /**
     * Will return the size, number of "bits", of this ShiftRegister.
     * 
     * @return The size, number of "bits", of this ShiftRegister.
     */
    @Override
    public int size() {
        return this.flipFlops.length;
    }

    /**
     * Get the output bit(s) of this shift register.
     * <br>
     * This will return the value in the tap of this ShiftRegister.<br>
     * The output byte will be 0x00 || 0x01.<br>
     * <br>
     * A call to this.getBits() may need to be called with the appropriate<br>
     * parameters before getOuput() is called.
     * 
     * @ensure result == 0x00 || result = 0x01
     */
    @Override
    public byte getOuput() {
        return this.tap;
    }

    /**
     * Get a bit form the register from the given position.
     * 
     * @require position >= 0 && position < this.size()
     * @ensure Will return the bit at the given position as a byte.<br>
     *         result == 0x00 || result = 0x01
     * @param position The position of the bit in this ShiftRegister
     * @return The bit at the given position.
     */
    @Override
    public byte getBitAt(int position) {
        return flipFlops[position].getValue();
    }

    /**
     * Load a value into the register. The register loads values from the left.<br>
     * <br>
     * The loaded value must be either 0x00 || 0x01
     * 
     * @require value == 0x00 || value == 0x01
     * @ensure the value loaded into the leftmost FlipFlop will be the the given value
     * @param value The value to be loaded into the register.
     */
    @Override
    public void loadValue(byte value) {
        flipFlops[0].tick(value);
    }

    /**
     * Get the bits from this ShiftRegister at the given positions.<br>
     * The returned array will contain the the bits in the same order that<br>
     * the positions were give.<br>
     * <br>
     * 
     * @require positions != null && positions[i] >= 0 && positions[n] < this.size()
     * @ensure will return the bits in the same order as the given positions
     * @param positions The positions of the bits to be returned
     */
    @Override
    public byte[] getBits(int... positions) {
        byte[] result = new byte[positions.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = flipFlops[positions[i]].getValue();
        }

        return result;
    }
}
