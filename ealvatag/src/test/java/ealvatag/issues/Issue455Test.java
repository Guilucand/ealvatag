package ealvatag.issues;

import ealvatag.AbstractTestCase;
import ealvatag.audio.AudioFile;
import ealvatag.audio.AudioFileIO;
import ealvatag.tag.FieldKey;
import ealvatag.tag.NullTag;

import java.io.File;

/**
 * Test
 */
public class Issue455Test extends AbstractTestCase {
    public void testMp4IsCompilationTrue() throws Exception {
        File orig = new File("testdata", "test1.m4a");
        if (!orig.isFile()) {
            System.err.println("Unable to test file - not available");
            return;
        }

        AudioFile mp4File = null;
        File testFile = AbstractTestCase.copyAudioToTmp("test1.m4a");

        Exception e = null;
        try {

            mp4File = AudioFileIO.read(testFile);
            mp4File.getTag().or(NullTag.INSTANCE).setField(FieldKey.IS_COMPILATION, "true");
        } catch (Exception ex) {
            ex.printStackTrace();
            e = ex;
        }
        assertNull(e);
        assertEquals("1", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

        //After Save
        mp4File.save();
        mp4File = AudioFileIO.read(testFile);
        assertEquals("1", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

    }


    public void testMp4IsCompilationTrue2() throws Exception {

        File orig = new File("testdata", "test1.m4a");
        if (!orig.isFile()) {
            System.err.println("Unable to test file - not available");
            return;
        }

        AudioFile mp4File = null;
        File testFile = AbstractTestCase.copyAudioToTmp("test1.m4a");

        Exception e = null;
        try {

            mp4File = AudioFileIO.read(testFile);
            mp4File.getTag().or(NullTag.INSTANCE).setField(FieldKey.IS_COMPILATION, "1");
        } catch (Exception ex) {
            ex.printStackTrace();
            e = ex;
        }
        assertNull(e);
        assertEquals("1", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

        //After Save
        mp4File.save();
        mp4File = AudioFileIO.read(testFile);
        assertEquals("1", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

    }


    public void testMp4IsCompilationFalse() throws Exception {
        File orig = new File("testdata", "test1.m4a");
        if (!orig.isFile()) {
            System.err.println("Unable to test file - not available");
            return;
        }

        AudioFile mp4File = null;
        File testFile = AbstractTestCase.copyAudioToTmp("test1.m4a");
        Exception e = null;
        try {

            mp4File = AudioFileIO.read(testFile);
            mp4File.getTag().or(NullTag.INSTANCE).setField(FieldKey.IS_COMPILATION, "false");
        } catch (Exception ex) {
            ex.printStackTrace();
            e = ex;
        }
        assertNull(e);
        assertEquals("0", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

        //After Save
        mp4File.save();
        mp4File = AudioFileIO.read(testFile);
        assertEquals("0", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));
    }

    public void testMp4IsCompilationFalse2() throws Exception {
        File orig = new File("testdata", "test1.m4a");
        if (!orig.isFile()) {
            System.err.println("Unable to test file - not available");
            return;
        }

        AudioFile mp4File = null;
        File testFile = AbstractTestCase.copyAudioToTmp("test1.m4a");
        Exception e = null;
        try {

            mp4File = AudioFileIO.read(testFile);
            mp4File.getTag().or(NullTag.INSTANCE).setField(FieldKey.IS_COMPILATION, "0");
        } catch (Exception ex) {
            ex.printStackTrace();
            e = ex;
        }
        assertNull(e);
        assertEquals("0", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

        //After Save
        mp4File.save();
        mp4File = AudioFileIO.read(testFile);
        
        assertEquals("0", mp4File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));
    }

    public void testMp3IsCompilationTrue() throws Exception {

        AudioFile mp3File = null;
        File testFile = AbstractTestCase.copyAudioToTmp("testV1.mp3", new File("testMp3IsCompilationTrue.mp3"));

        Exception e = null;
        try {

            mp3File = AudioFileIO.read(testFile);
            mp3File.getTagOrSetNewDefault().setField(FieldKey.IS_COMPILATION, "true");
        } catch (Exception ex) {
            ex.printStackTrace();
            e = ex;
        }
        assertNull(e);
        assertEquals("true", mp3File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

        //After Save
        mp3File.save();
        mp3File = AudioFileIO.read(testFile);
        assertEquals("true", mp3File.getTagOrSetNewDefault().getFirst(FieldKey.IS_COMPILATION));

    }

    /**
     * set properly when use function
     */
    public void testMp3IsCompilationTrue2() throws Exception {
        AudioFile mp3File = null;
        File testFile = AbstractTestCase.copyAudioToTmp("testV1.mp3", new File("testMp3IsCompilationTrue2.mp3"));

        Exception e = null;
        try {

            mp3File = AudioFileIO.read(testFile);
            mp3File.getTagOrSetNewDefault();
            mp3File.getTag().or(NullTag.INSTANCE).setField(FieldKey.IS_COMPILATION, "1");
        } catch (Exception ex) {
            ex.printStackTrace();
            e = ex;
        }
        assertNull(e);
        assertEquals("1", mp3File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

        //After Save
        mp3File.save();
        mp3File = AudioFileIO.read(testFile);
        assertEquals("1", mp3File.getTagOrSetNewDefault().getFirst(FieldKey.IS_COMPILATION));

    }


    public void testMp3IsCompilationFalse() throws Exception {
        AudioFile mp3File = null;
        File testFile = AbstractTestCase.copyAudioToTmp("testV1.mp3", new File("testMp3IsCompilationFalse.mp3"));
        Exception e = null;
        try {

            mp3File = AudioFileIO.read(testFile);
            mp3File.getTagOrSetNewDefault().setField(FieldKey.IS_COMPILATION, "false");
        } catch (Exception ex) {
            ex.printStackTrace();
            e = ex;
        }
        assertNull(e);
        assertEquals("false", mp3File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));

        //After Save
        mp3File.save();
        mp3File = AudioFileIO.read(testFile);
        assertEquals("false", mp3File.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.IS_COMPILATION));
    }

}