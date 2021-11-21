package com.leetcode;

/*
https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
Hard. String.

Given a file and assume that you can only read the file using a given method r
ead4, implement a method read to read n characters. Your method read may be
called multiple times.

Method read4:

The API read4 reads 4 consecutive characters from the file, then writes those
characters into the buffer array buf.

The return value is the number of actual characters read.

Note that read4() has its own file pointer, much like FILE *fp in C.

Definition of read4:

    Parameter:  char[] buf4
    Returns:    int

Note: buf4[] is destination not source, the results from read4 will be copied
to buf4[]

Below is a high level example of how read4 works:

File file("abcde"); // File is "abcde", initially file pointer (fp) points to
'a'
char[] buf = new char[4]; // Create buffer with enough space to store
characters
read4(buf4); // read4 returns 4. Now buf = "abcd", fp points to 'e'
read4(buf4); // read4 returns 1. Now buf = "e", fp points to end of file
read4(buf4); // read4 returns 0. Now buf = "", fp points to end of file


Method read:

By using the read4 method, implement the method read that reads n characters
from the file and store it in the buffer array buf. Consider that you cannot
manipulate the file directly.

The return value is the number of actual characters read.

Definition of read:

    Parameters:	char[] buf, int n
    Returns:	int

Note: buf[] is destination not source, you will need to write the results to
buf[]

Example 1:

File file("abc");
Solution sol;
// Assume buf is allocated and guaranteed to have enough space for storing all
characters from the file.
sol.read(buf, 1); // After calling your read method, buf should contain "a".
We read a total of 1 character from the file, so return 1.
sol.read(buf, 2); // Now buf should contain "bc". We read a total of 2
characters from the file, so return 2.
sol.read(buf, 1); // We have reached the end of file, no more characters can
be read. So return 0.

Example 2:

File file("abc");
Solution sol;
sol.read(buf, 4); // After calling your read method, buf should contain "abc".
We read a total of 3 characters from the file, so return 3.
sol.read(buf, 1); // We have reached the end of file, no more characters can
be read. So return 0.

Note:

Consider that you cannot manipulate the file directly, the file is only
accesible for {@code read4} but not for `read`.
The read function may be called multiple times.
Please remember to RESET your class variables declared in Solution, as static
/class variables are persisted across multiple test cases. Please see here for
more details.
You may assume the destination buffer array, buf, is guaranteed to have
enough space for storing n characters.
It is guaranteed that in a given test case the same buffer buf is called
by read.
*/

/**
 * Mocking of the read4 API is defined in the parent class Reader4.
 * int read4(char[] buf4);
 */
class Reader4 {
    int read4(final char[] buf4) {
        final java.util.concurrent.ThreadLocalRandom rand =
                java.util.concurrent.ThreadLocalRandom.current();
        final int n = rand.nextInt(3, 5);
        for (int i = 0; i < n; i++) {
            buf4[i] = (char) rand.nextInt('a', 'z' + 1);
        }
        return n;
    }
}

class _158_ReadNCharactersGivenRead4IiCallMultipleTimes extends Reader4 {

    private static final int R = 4;  // reads 4 consecutive characters
    private final char[] buf4 = new char[R];
    private int prevIndexBuf4 = 0;
    private int lenBuf4 = 0;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        int index = 0;
        boolean eof = false;

        // if the last read, the rest of buf4 are to be dealt with
        if (this.prevIndexBuf4 > 0) {
            while (this.prevIndexBuf4 < this.lenBuf4 && index < n) {
                buf[index++] = this.buf4[this.prevIndexBuf4++];
            }
            // clear restored state
            if (this.prevIndexBuf4 == lenBuf4) {
                this.lenBuf4 = 0;
                this.prevIndexBuf4 = 0;
            }
        }

        // main loop
        while (!eof && index < n) {
            int count = read4(buf4);
            if (count < R) {
                eof = true;
            }

            // pointer to the rest of buf4 for next read
            if (n - index < count) {
                this.prevIndexBuf4 = n - index;
                this.lenBuf4 = count;
            }

            count = Math.min(count, n - index);
            for (int i = 0; i < count; i++) {
                buf[index++] = buf4[i];
            }
        }
        return index;
    }
}
