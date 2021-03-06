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

package jtrivium.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import jtrivium.cipher.JTrivium;

/**
 *
 * @author daniel
 */
public class FileEncrypt {
    
    public static final int MAX_READ_BUFFER_SIZE = 2048;
    
    JTrivium cipher;
    DataInputStream reader;
    DataOutputStream writer;
    
    public FileEncrypt(String inputFilePath, String outputFilePath, JTrivium cipher) throws FileNotFoundException {
        this.cipher = cipher;
        
        reader = new DataInputStream(new FileInputStream(new File(inputFilePath)));
        writer = new DataOutputStream(new FileOutputStream(new File(outputFilePath)));
    }

    public void encrypt() throws IOException {
        int readBytes = 0;
        
        byte[] buffer = new byte[FileEncrypt.MAX_READ_BUFFER_SIZE];
        
        do {
            readBytes = reader.read(buffer, 0, FileEncrypt.MAX_READ_BUFFER_SIZE);
            
            for (int i = 0; i < readBytes; i++) {
                buffer[i] ^= cipher.getKeyByte();
            }

            if (readBytes > 0) {
                writer.write(buffer, 0, readBytes);
                writer.flush();
            }
            
        } while (readBytes > 0);
    }
    
    public void close() throws IOException{
        reader.close();
        writer.close();
    }
    
}
