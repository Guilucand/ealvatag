package ealvatag.issues;

import ealvatag.TestUtil;
import ealvatag.audio.AudioFile;
import ealvatag.audio.AudioFileIO;
import ealvatag.tag.FieldKey;
import ealvatag.tag.NullTag;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Test reading of track without total for mp4
 */
public class Issue380Test {
    @After public void tearDown() {
        TestUtil.deleteTestDataTemp();
    }

    @Test public void testIssue() throws Exception {
        Exception caught = null;
        try {
            File orig = new File("testdata", "test98.m4a");
            if (!orig.isFile()) {
                System.err.println("Unable to test file - not available" + orig);
                return;
            }

            File testFile = TestUtil.copyAudioToTmp("test98.m4a");
            AudioFile af = AudioFileIO.read(testFile);
            Assert.assertEquals("", af.getTag().or(NullTag.INSTANCE).getFirst(FieldKey.TRACK_TOTAL));
        } catch (Exception e) {
            caught = e;
            e.printStackTrace();
        }
        Assert.assertNull(caught);
    }
}
