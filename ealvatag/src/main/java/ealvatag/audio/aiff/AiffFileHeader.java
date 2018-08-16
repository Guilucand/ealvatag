package ealvatag.audio.aiff;

import com.ealva.ealvalog.java.JLogger;
import com.ealva.ealvalog.java.JLoggers;
import ealvatag.audio.Utils;
import ealvatag.audio.exceptions.CannotReadException;
import ealvatag.logging.Hex;
import ealvatag.logging.Log;

import static com.ealva.ealvalog.LogLevel.ERROR;
import static ealvatag.audio.aiff.AiffType.AIFC;
import static ealvatag.audio.aiff.AiffType.AIFF;
import static ealvatag.audio.iff.IffHeaderChunk.HEADER_LENGTH;
import static ealvatag.audio.iff.IffHeaderChunk.TYPE_LENGTH;
import static java.nio.ByteOrder.BIG_ENDIAN;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>
 * Aiff File Header always consists of
 * </p>
 * <ul>
 * <li>ckID - always FORM</li>
 * <li>chSize - size in 4 bytes</li>
 * <li>formType - currently either AIFF or AIFC, see {@link AiffType}</li>
 * <li>chunks[] - an array of chunks</li>
 * </ul>
 */
public class AiffFileHeader {
  private static final String FORM = "FORM";
  private static JLogger LOG = JLoggers.get(AiffFileHeader.class, Log.MARKER);

  /**
   * Reads the file header and registers the data (file type) with the given header.
   *
   * @param fc              random access file
   * @param aiffAudioHeader the {@link ealvatag.audio.AudioHeader} we set the read data to
   * @param fileName        the name of the file the FileChannel represents
   *
   * @return the number of bytes in the FORM chunk, i.e. the size of the payload
   *
   * @throws IOException         thrown if there was an error reading from the channel
   * @throws CannotReadException if the file is not a valid AIFF file
   */
  public long readHeader(FileChannel fc, final AiffAudioHeader aiffAudioHeader, String fileName) throws IOException, CannotReadException {
    final ByteBuffer headerData = ByteBuffer.allocateDirect(HEADER_LENGTH);
    headerData.order(BIG_ENDIAN);
    final int bytesRead = fc.read(headerData);
    headerData.position(0);

    if (bytesRead < HEADER_LENGTH) {
      throw new IOException(
          fileName + " AIFF:Unable to read required number of databytes read:" + bytesRead + ":required:" +
              HEADER_LENGTH);
    }

    final String signature = Utils.readFourBytesAsChars(headerData);
    if (FORM.equals(signature)) {
      // read chunk size
      final long chunkSize = headerData.getInt();
      LOG.log(ERROR, "$1%s Reading AIFF header size:$2%d 0x$2%x", fileName, chunkSize);

      readFileType(headerData, aiffAudioHeader);
      // subtract the file type length from the chunk size to get remaining number of bytes
      return chunkSize - TYPE_LENGTH;
    } else {
      throw new CannotReadException(fileName + "Not an AIFF file: incorrect signature " + signature);
    }
  }

  /**
   * Reads the file type ({@link AiffType}).
   *
   * @throws CannotReadException if the file type is not supported
   */
  private void readFileType(final ByteBuffer bytes, final AiffAudioHeader aiffAudioHeader)
      throws IOException, CannotReadException {
    final String type = Utils.readFourBytesAsChars(bytes);
    if (AIFF.getCode().equals(type)) {
      aiffAudioHeader.setFileType(AIFF);
    } else if (AIFC.getCode().equals(type)) {
      aiffAudioHeader.setFileType(AIFC);
    } else {
      throw new CannotReadException("Invalid AIFF file: Incorrect file type info " + type);
    }
  }
}
