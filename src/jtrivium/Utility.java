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
 * This class provides some utility function to convert hexadecimal to String,
 * and a String representation of hexadecimal to hexadecimal.
 * 
 * See http://www.ecrypt.eu.org/stream/triviumpf.html <br>
 * See http://www.ecrypt.eu.org/stream/p3ciphers/trivium/trivium_p3.pdf 
 * 
 * @author Daniel Ward - dwa012@gmail.com
 * @date October 30, 2011
 * @version 0.1
 */
public class Utility {

    public static byte[] stringToHex(String string) {
        String s = string.replaceAll(" ", "");
        int length = s.length() / 2;
        int count = 0;

        byte[] result = new byte[length];

        for (int i = 0; i < s.length(); i = i + 2) {
            byte temp = (byte) ((charToHex(s.charAt(i)) << 4) | charToHex(s.charAt(i + 1)));
            result[count] = temp;
            count++;
        }

        return result;

    }
    
    public static byte[] swapEndianness(byte[] bytes) {
        byte[] result = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            result[i] = bytes[bytes.length - 1 - i];
        }

        return result;
    }

    public static String byteToHexString(byte b) {
        String result = "";

        byte upperByte = (byte) ((b & 0xf0) >> 4);

        result += byteToChar(upperByte);

        byte lowerByte = (byte) ((b & 0x0f));

        result += byteToChar(lowerByte);

        return result;
    }

    private static char byteToChar(byte c) {
        char result = '0';

        if (c <= 9 && c >= 0) {
            result = (char) (c + '0');
        }

        if (c <= 15 && c >= 10) {
            result = (char) (c - 10 + 'A');
        }

        return result;
    }

    private static byte charToHex(char c) {
        byte result = 0x00;

        if (c <= '9' && c >= '0') {
            result = (byte) (c - '0');
        }

        if (c <= 'F' && c >= 'A') {
            result = (byte) (c - 'A' + 10);
        }

        if (c <= 'f' && c >= 'a') {
            result = (byte) (c - 'a' + 10);
        }

        return result;
    }
}
