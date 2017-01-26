/*
 *  MusicTag Copyright (C)2003,2004
 *
 *  This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser
 *  General Public  License as published by the Free Software Foundation; either version 2.1 of the License,
 *  or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 *  the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *  See the GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License along with this library; if not,
 *  you can get a copy from http://www.opensource.org/licenses/lgpl-license.php or write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package ealvatag.tag.id3.framebody;

import ealvatag.tag.InvalidTagException;
import ealvatag.tag.id3.ID3v24Frames;
import okio.Buffer;

import java.nio.ByteBuffer;

/**
 * Publishers official webpage URL link frames.
 * <p>The 'Publishers official webpage' frame is a URL pointing at the official wepage for the publisher.
 * <p>
 * <p>For more details, please refer to the ID3 specifications:
 * <ul>
 * <li><a href="http://www.id3.org/id3v2.3.0.txt">ID3 v2.3.0 Spec</a>
 * </ul>
 *
 * @author : Paul Taylor
 * @author : Eric Farng
 * @version $Id$
 */
public class FrameBodyWPUB extends AbstractFrameBodyUrlLink implements ID3v24FrameBody, ID3v23FrameBody {
    /**
     * Creates a new FrameBodyWPUB datatype.
     */
    public FrameBodyWPUB() {
    }

    /**
     * Creates a new FrameBodyWPUB datatype.
     *
     * @param urlLink
     */
    public FrameBodyWPUB(String urlLink) {
        super(urlLink);
    }

    public FrameBodyWPUB(FrameBodyWPUB body) {
        super(body);
    }

    /**
     * Creates a new FrameBodyWPUB datatype.
     *
     * @param byteBuffer
     * @param frameSize
     *
     * @throws java.io.IOException
     * @throws InvalidTagException
     */
    public FrameBodyWPUB(ByteBuffer byteBuffer, int frameSize) throws InvalidTagException {
        super(byteBuffer, frameSize);
    }

    public FrameBodyWPUB(Buffer byteBuffer, int frameSize) throws InvalidTagException {
        super(byteBuffer, frameSize);
    }

    /**
     * The ID3v2 frame identifier
     *
     * @return the ID3v2 frame identifier  for this frame type
     */
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_URL_PUBLISHERS;
    }
}