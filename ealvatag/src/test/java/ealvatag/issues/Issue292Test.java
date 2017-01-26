package ealvatag.issues;

import com.google.common.io.Files;
import ealvatag.AbstractTestCase;
import ealvatag.audio.AudioFile;
import ealvatag.audio.AudioFileIO;
import ealvatag.tag.FieldKey;
import ealvatag.tag.NullTag;

import java.io.File;

/**
 * Unable to save changes to file if backup .old file already exists
 */
public class Issue292Test extends AbstractTestCase
{
    public void testSavingMp3File()
    {
        File testFile = AbstractTestCase.copyAudioToTmp("testV1Cbr128ID3v2.mp3");
        if (!testFile.isFile())
        {
            System.err.println("Unable to test file - not available");
            return;
        }

        File originalFileBackup = null;

        Exception exceptionCaught = null;
        try
        {

            testFile = AbstractTestCase.copyAudioToTmp("testV1Cbr128ID3v2.mp3");
            //Put file in backup location
            originalFileBackup = new File(testFile.getAbsoluteFile().getParentFile().getPath(), Files.getNameWithoutExtension(testFile.getPath()) + ".old");
            testFile.renameTo(originalFileBackup);

            //Copy over again
            testFile = AbstractTestCase.copyAudioToTmp("testV1Cbr128ID3v2.mp3");

            //Read and save chnages
            AudioFile af = AudioFileIO.read(testFile);
            af.getTag().or(NullTag.INSTANCE).setField(FieldKey.ARTIST, "fredqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
            af.getTag().or(NullTag.INSTANCE).setField(FieldKey.AMAZON_ID,"fredqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

            af.save();

            af = AudioFileIO.read(testFile);
            assertEquals("fredqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",af.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.ARTIST));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            exceptionCaught = e;
        }
        finally
        {
            originalFileBackup.delete();
        }
        assertNull(exceptionCaught);
    }

    public void testSavingMp4File()
    {
        File testFile = AbstractTestCase.copyAudioToTmp("test8.m4a");
        if (!testFile.isFile())
        {
            System.err.println("Unable to test file - not available");
            return;
        }

        File originalFileBackup = null;

        Exception exceptionCaught = null;
        try
        {

            testFile = AbstractTestCase.copyAudioToTmp("test8.m4a");
            //Put file in backup location
            originalFileBackup = new File(testFile.getAbsoluteFile().getParentFile().getPath(), Files.getNameWithoutExtension(testFile.getPath()) + ".old");
            testFile.renameTo(originalFileBackup);

            //Copy over again
            testFile = AbstractTestCase.copyAudioToTmp("test8.m4a");

            //Read and save chnages
            AudioFile af = AudioFileIO.read(testFile);
            af.getTag().or(NullTag.INSTANCE).setField(FieldKey.ARTIST,"fredqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
            af.getTag().or(NullTag.INSTANCE).setField(FieldKey.AMAZON_ID,"fredqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

            af.save();

            af = AudioFileIO.read(testFile);
            assertEquals("fredqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",af.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.ARTIST));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            exceptionCaught = e;
        }
        finally
        {
            originalFileBackup.delete();
        }
        assertNull(exceptionCaught);
    }

}