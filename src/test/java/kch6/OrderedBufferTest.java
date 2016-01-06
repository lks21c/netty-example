package kch6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by lks21c on 15. 8. 25.
 */
public class OrderedBufferTest {

	String sourceData = "hello world";

	@Test
	public void testNettyBufferToNioBuffer() throws Exception {
		ByteBuf buf = Unpooled.buffer(11);

		buf.writeBytes(sourceData.getBytes());
		assertEquals(sourceData, buf.toString(Charset.defaultCharset()));

		ByteBuffer nioByteBuffer = buf.nioBuffer();
		assertNotNull(nioByteBuffer);
		assertEquals(sourceData,
				new String(nioByteBuffer.array(), nioByteBuffer.arrayOffset(), nioByteBuffer.remaining()));
	}

	@Test
	public void testNioBufferToNettyBuffer() throws Exception {
		ByteBuffer byteBuffer = ByteBuffer.wrap(sourceData.getBytes());
		ByteBuf nettyBuffer = Unpooled.wrappedBuffer(byteBuffer);

		assertEquals(sourceData, nettyBuffer.toString(Charset.defaultCharset()));
	}
}
