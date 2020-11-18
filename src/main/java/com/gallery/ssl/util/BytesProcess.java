package com.gallery.ssl.util;

import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class BytesProcess {

    public static Byte[] compressImageBytes(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] bufferArray = new byte[1024];
        while (!deflater.finished()) {
            int counter = deflater.deflate(bufferArray);
            arrayOutputStream.write(bufferArray, 0, counter);
        }
        try {
            arrayOutputStream.close();
        } catch (IOException e) {
        }
        return ArrayUtils.toObject(arrayOutputStream.toByteArray());
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int counter = inflater.inflate(buffer);
                arrayOutputStream.write(buffer, 0, counter);
            }
            arrayOutputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return arrayOutputStream.toByteArray();
    }
}
