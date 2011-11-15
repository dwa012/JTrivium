package jtrivium.hardware;

/**
 * The class models a recursively chained FlipFlop.
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
public class FlipFlop {

    private boolean hasRightNeighbor;//used to see if this FlopFlop has a neighbor
    private FlipFlop rightNeighbor;//used to reference the right neighbor
    private byte value;//the value of this FlipFlop 

    /**
     * Creates a FlipFlop without neighbor on the right.
     * 
     * @ensure this.getValue() == 0x00 && this.getRightNeighbor() == null
     */
    public FlipFlop() {
        value = 0;
        hasRightNeighbor = false;

    }

    /**
     * Creates a new FlipFlop with the neighbor on the right.
     * 
     * @require neighbor != null
     * @ensure this.getValue() == 0x00 && this.getRightNeighbor() == neighbor
     * @param neighbor The FlipFlop that will be to the right of this FlipFlop.
     */
    public FlipFlop(FlipFlop neighbor) {
        value = 0;
        hasRightNeighbor = true;
        this.rightNeighbor = neighbor;
    }

    /**
     * Causes this FlipFlop to give its value to its right neighbor (if it has
     * a neighbor). It will then update its value with the given value.
     * 
     * @require value != null && (value == 0x01 || value == 0x00)
     * @ensure this.getValue() == value && <br>
     *         this.getRightNeighbor().getValue() = old this.getValue()
     * @param value The value that this FlipFlop will now contain
     */
    public void tick(byte value) {
        //if this FlipFlop has a neighbor, then cause it to tick
        if (hasRightNeighbor) {
            rightNeighbor.tick(this.value);
        }

        this.value = value;
    }

    /**
     * Return a reference to this FlipFlop's right neighbor.
     * 
     * @ensure iff this has a right neighbor, then this.getRightNeighbor() != null
     * @return Will return a reference to this FlipFlop's right neighbor. <br>
     *         If this FlipFlop has right neighbor, then the returned <br>
     *         reference will be != null, else it will be null.
     */
    public FlipFlop getRightNeighbor() {
        return rightNeighbor;
    }

    /**
     * Return the value contained in this FlipFlop.
     * 
     * @ensure The return will be 0x01 || 0x00 
     * @return Will return the value contained in this FlipFlop
     */
    public byte getValue() {
        return value;
    }
}
