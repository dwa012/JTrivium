package jtrivium;

/**
 * The interface outlines the basic structure of a shift register.
 * 
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
 * 
 * @author Daniel Ward - dwa012@gmail.com
 * @date October 30, 2011
 * @version 0.1
 */
public interface ShiftRegister {
    
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
     * Load a value into the register. The register loads values from the left.<br>
     * <br>
     * All implementing classes must indicate how many bits can be loaded at one time.
     * 
     * @param value The value to be loaded into the register.
     */
    public void loadValue(byte value);
    
    /**
     * Set the tap values based on the given number of location in the register.<br>
     * <br>
     * All implementing classes must specify the number of allowed tap locations.<br>
     * and how the locations will be used.
     * 
     * @param tapLocations The tap locations. The implementing class will indicate<br>
     *                     how many parameters are accepted and how they are used.
     */
    public void setTapValue(int... tapLocations); 
    
}
