package ealvatag.audio.generic;

import com.google.common.io.Files;
import ealvatag.audio.Utils;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;


public class UtilsTest  extends TestCase {

    public void testGetExtension () {
        assertEquals ("jpeg", Files.getFileExtension(new File("_12XYZ.jpeg").getName()));
    }

    public void testReadUInt16 () {
        try {
            byte[] maxUnsignedBuf = { (byte) 0xFF, (byte) 0xFF };
            ByteArrayInputStream ins = new ByteArrayInputStream (maxUnsignedBuf);
            DataInputStream dis = new DataInputStream (ins);
            int val = Utils.readUint16(dis);
            assertEquals (val, 0xFFFF);

            byte[] smallIntBuf = { (byte) 0x01, (byte) 0x10 };
            ins = new ByteArrayInputStream (smallIntBuf);
            dis = new DataInputStream (ins);
            val = Utils.readUint16(dis);
            assertEquals (val, 0x0110);
        }
        catch (IOException e) {
            fail("IOException in testReadUInt16");  // huh?
        }
    }

    public void testReadUInt32 () {
        try {
            byte[] maxUnsignedBuf = { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
            ByteArrayInputStream ins = new ByteArrayInputStream (maxUnsignedBuf);
            DataInputStream dis = new DataInputStream (ins);
            long val = Utils.readUint32(dis);
            assertEquals (val, 0xFFFFFFFFL);

            byte[] smallIntBuf = { (byte) 0x03, (byte) 0xFF, (byte) 0x01, (byte) 0x12 };
            ins = new ByteArrayInputStream (smallIntBuf);
            dis = new DataInputStream (ins);
            val = Utils.readUint32(dis);
            assertEquals (val, 0x03FF0112);
        }
        catch (IOException e) {
            fail("IOException in testReadUInt32");  // huh?
        }
    }

    public void testReadString () {
        try {
            byte [] strBuf = { (byte) 'A', (byte) '=', (byte) '1', (byte) '+', (byte) '7' };
            ByteArrayInputStream ins = new ByteArrayInputStream (strBuf);
            DataInputStream dis = new DataInputStream (ins);
            String s = Utils.readString(dis,  5);
            assertEquals ("A=1+7", s);
        }
        catch (IOException e) {
            fail("IOException in testReadString");  // huh?
        }
    }

    public void testGetSizeLEInt32 () {
        byte[] bytes = Utils.getSizeLEInt32(0x010203F4);
        assertEquals (bytes.length, 4);
        // need to AND with FF to avoid sign extension of byte
        assertEquals (bytes[0] & 0xFF, 0xF4);
        assertEquals (bytes[1] & 0xFF, 0x03);
        assertEquals (bytes[2] & 0xFF, 0x02);
        assertEquals (bytes[3] & 0xFF, 0x01);
    }

    public void testGetSizeBEInt32 () {
        byte[] bytes = Utils.getSizeBEInt32(0x010203F4);
        assertEquals (bytes.length, 4);
        // need to AND with FF to avoid sign extension of byte
        assertEquals (bytes[3] & 0xFF, 0xF4);
        assertEquals (bytes[2] & 0xFF, 0x03);
        assertEquals (bytes[1] & 0xFF, 0x02);
        assertEquals (bytes[0] & 0xFF, 0x01);
    }

    public void testGetSizeBEInt16 () {
        byte[] bytes = Utils.getSizeBEInt16((short) 0x0182);
        assertEquals (bytes.length, 2);
        // need to AND with FF to avoid sign extension of byte
        assertEquals (bytes[1] & 0xFF, 0x82);
        assertEquals (bytes[0] & 0xFF, 0x01);
    }

    public void testgetLongLE () {
        ByteBuffer bb = ByteBuffer.allocate(4);
        byte[] bytes = new byte[] {0x32, 0x01, (byte)0xF0, 0x18};
        bb.put(bytes);
        long val = Utils.getLongLE (bb, 0, 3);
        assertEquals (0x18F00132L, val);
    }

    public void testgetLongBE () {
        ByteBuffer bb = ByteBuffer.allocate(4);
        byte[] bytes = new byte[] {0x32, 0x01, (byte) 0xF0, 0x18};
        bb.put(bytes);
        long val = Utils.getLongBE (bb, 0, 3);
        assertEquals (0x3201F018L, val);

        bytes = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08};
        bb = ByteBuffer.allocate(8);
        bb.put(bytes);
        val = Utils.getLongBE (bb, 0, 7);
        assertEquals (0x0102030405060708L, val);
    }

    public void testGetIntLE () {
        byte[] bytes = new byte[] {(byte) 0xFF, 0x01, 0x11, 0x21};
        int val = Utils.getIntLE (bytes);
        assertEquals (0x211101FF, val);

        bytes = new byte[] {0, (byte) 0xFF, 0x01, 0x11, 0x31, 0};
        val = Utils.getIntLE (bytes, 1, 4);
        assertEquals (0x311101FF, val);
    }

    public void testGetIntBE () {
        // But there is one with a ByteBuffer, a start, and an end
        ByteBuffer bb = ByteBuffer.allocate(5);
        byte[] bytes = new byte[] {0, 0x32, (byte) 0x80, 0x70, 0x18};
        bb.put(bytes);
        int val = Utils.getIntBE(bb, 1, 4);
        assertEquals (0x32807018, val);
    }

    public void testGetShortBE()
    {
        ByteBuffer bb = ByteBuffer.allocate(8);
        byte[] bytes = new byte[] {(byte)0xFF, (byte)0xFF};
        bb.put(bytes);
        bb.rewind();
        short val = Utils.getShortBE(bb,1,2);
        System.out.println(val);
    }

}