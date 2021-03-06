package ealvatag.tag.id3;

import ealvatag.tag.id3.framebody.FrameBodyTDEN;
import ealvatag.tag.id3.valuepair.TextEncoding;
import org.junit.Assert;
import org.junit.Test;

/**
 */
public class FrameTDENTest {
    @Test public void testID3Specific() throws Exception {
        Exception e = null;
        try {
            ID3v24Tag tag = new ID3v24Tag();
            ID3v24Frame frame = new ID3v24Frame("TDEN");
            frame.setBody(new FrameBodyTDEN(TextEncoding.ISO_8859_1, "11:10"));
            tag.addFrame(frame);
            Assert.assertEquals("11:10", tag.getFirst("TDEN"));
        } catch (Exception ex) {
            e = ex;
            ex.printStackTrace();
        }
        Assert.assertNull(e);
    }

}
