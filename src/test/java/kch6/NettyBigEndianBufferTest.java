package kch6;

import static org.junit.Assert.assertEquals;

import java.nio.ByteOrder;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by lks21c on 15. 8. 25.
 */
public class NettyBigEndianBufferTest {

	@Test
	public void test1() throws Exception {
		ByteBuf buf = Unpooled.buffer(11);
		assertEquals(ByteOrder.BIG_ENDIAN, buf.order());

		buf.writeShort(1);

		buf.markReaderIndex();
		assertEquals(1, buf.readShort());

		buf.resetReaderIndex();

		ByteBuf littleEndianBuf = buf.order(ByteOrder.LITTLE_ENDIAN);
		assertEquals(256, littleEndianBuf.readShort());
	}
}
