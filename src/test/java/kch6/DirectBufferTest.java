package kch6;

import static org.junit.Assert.assertEquals;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

import org.junit.Test;

/**
 * Created by lks21c on 15. 8. 25.
 */
public class DirectBufferTest {

	@Test
	public void testCreateDirectBuffer() throws Exception {
		CharBuffer heapBuffer = CharBuffer.allocate(11);
		assertEquals(11, heapBuffer.capacity());
		assertEquals(false, heapBuffer.isDirect());

		ByteBuffer directBuffer = ByteBuffer.allocateDirect(11);
		assertEquals(11, directBuffer.capacity());
		assertEquals(true, directBuffer.isDirect());

		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0 };
		IntBuffer intHeapBuffer = IntBuffer.wrap(array);
		assertEquals(11, intHeapBuffer.capacity());
		assertEquals(false, intHeapBuffer.isDirect());
	}

	@Test
	public void testByteBuffer1() throws Exception {
		ByteBuffer directBuffer = ByteBuffer.allocateDirect(11);
		System.out.println("byte buffer init value : " + directBuffer);

		byte[] source = "Hello world".getBytes();
		directBuffer.put(source);
		System.out.println("aftet write 11 bytes " + directBuffer);
	}

	@Test
	public void testByteBuffer3() throws Exception {
		ByteBuffer directBuffer = ByteBuffer.allocateDirect(11);
		System.out.println("byte buffer init value : " + directBuffer);

		directBuffer.put((byte) 1);
		System.out.println(directBuffer.get());
		System.out.println(directBuffer);

	}

	@Test
	public void testRightByteBuffer3() throws Exception {
		ByteBuffer directBuffer = ByteBuffer.allocateDirect(11);
		System.out.println("byte buffer init value : " + directBuffer);

		directBuffer.put((byte) 1);
		directBuffer.put((byte) 2);
		assertEquals(2, directBuffer.position());

		directBuffer.rewind();
		assertEquals(0, directBuffer.position());

		assertEquals(1, directBuffer.get());
		assertEquals(1, directBuffer.position());

		System.out.println(directBuffer);
	}

	@Test
	public void testWriteByteBuffer() throws Exception {
		ByteBuffer directBuffer = ByteBuffer.allocateDirect(11);

		directBuffer.put((byte) 1);
		directBuffer.put((byte) 2);
		directBuffer.put((byte) 3);
		directBuffer.put((byte) 4);
		assertEquals(4, directBuffer.position());
		assertEquals(11, directBuffer.limit());

		directBuffer.flip();
		assertEquals(0, directBuffer.position());
		assertEquals(4, directBuffer.limit());
	}

	@Test
	public void testReadByteBuffer() throws Exception {
		byte[] tempArray = { 1, 2, 3, 4, 5, 0, 0, 0, 0, 0, 0 };
		ByteBuffer directBuffer = ByteBuffer.wrap(tempArray);
		assertEquals(1, directBuffer.get());
		assertEquals(2, directBuffer.get());
		assertEquals(3, directBuffer.get());
		assertEquals(4, directBuffer.get());
		assertEquals(4, directBuffer.position());
		assertEquals(11, directBuffer.limit());

		directBuffer.flip();
		assertEquals(0, directBuffer.position());
		assertEquals(4, directBuffer.limit());

		directBuffer.get(3);

		assertEquals(0, directBuffer.position());
	}
}
