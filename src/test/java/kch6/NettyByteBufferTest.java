package kch6;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * Created by lks21c on 15. 8. 25.
 */
public class NettyByteBufferTest {

	@Test
	public void test1() throws Exception {
		ByteBuf buf = Unpooled.buffer(11);
		testBuffer(buf, false);
	}

	@Test
	public void test2() throws Exception {
		ByteBuf buf = Unpooled.directBuffer(11);
		testBuffer(buf, true);
	}

	@Test
	public void test3() throws Exception {
		ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(11);
		testBuffer(buf, false);
	}

	@Test
	public void test4() throws Exception {
		ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(11);
		testBuffer(buf, true);
	}

	private void testBuffer(ByteBuf buf, boolean isDirect) {
		assertEquals(11, buf.capacity());
		assertEquals(isDirect, buf.isDirect());
		assertEquals(0, buf.readableBytes());
		assertEquals(11, buf.writableBytes());
	}
}
