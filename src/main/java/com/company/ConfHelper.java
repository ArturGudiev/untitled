/* $Id: ConfHelper.java 91979 2014-12-02 19:03:43Z fountt1 $
 *
 * This computer code is copyright 2004-2011 EMC Corporation.
 * All rights reserved
 */

package com.company;

// CIPHER / GENERATORS
// EXCEPTIONS
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;

public class ConfHelper {

    private static SecretKey secretKey;
    private static IvParameterSpec iv;
    private static Cipher cipher;

    static {
        try {
            final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            /*
             * only 128 bit to overcome probelms with export license for higher levels of cipher
             */
            keyGenerator.init(128);
            secretKey = new SecretKeySpec(new byte[] { (byte) 0xc7, (byte) 0x73, (byte) 0x21, (byte) 0x8c, (byte) 0x7e, (byte) 0xc8, (byte) 0xee, (byte) 0x99,
                    (byte) 0x32, (byte) 0xaa, (byte) 0x45, (byte) 0xd3, (byte) 0x21, (byte) 0xfd, (byte) 0xee, (byte) 0x69 }, "AES");

            final byte[] ivBytes = new byte[] { 0x10, 0x15, 0x16, 0x02, 0x3A, 0x46, 0x32, 0x1D, 0x2a, 0x4C, 0x5E, 0x7E, 0x7B, 0x21, 0x1E, 0x3A };
            iv = new IvParameterSpec(ivBytes);

        } catch (final NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

   public static String encrypt(final String encryptStr) {
        try {
            if (encryptStr == null) {
                // System.err.println("[ERROR] Encryption string is null") ;
                return null;
            }

            /*
             * convert string to array of bytes
             */
            final byte[] input = encryptStr.getBytes(Charset.forName("UTF8"));

            /*
             * create the cipher
             */
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

            /*
             * encrypt
             */
            final byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
            int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
            ctLength += cipher.doFinal(cipherText, ctLength);

            /*
             * Encode bytes to base64 to get a string
             */
            return Base64.encodeBytes(cipherText).trim();

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public String get(String encryptStr){
//        try {
//            if (encryptStr == null) {
//                // System.err.println("[ERROR] Encryption string is null") ;
//                return null;
//            }
//
//            /*
//             * convert string to array of bytes
//             */
//            final byte[] input = encryptStr.getBytes(Charset.forName("UTF8"));
//
//            /*
//             * create the cipher
//             */
//            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
//
//            /*
//             * encrypt
//             */
//            final byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
//            int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
//            ctLength += cipher.doFinal(cipherText, ctLength);
//
//            /*
//             * Encode bytes to base64 to get a string
//             */
//            return Base64.encodeBytes(cipherText).trim();
//
//        } catch (final Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

//    public String get2(String decryptStr){
//        try {
//            if (decryptStr == null) {
//                // System.err.println("[ERROR] Decryption string is null") ;
//                return null;
//            }
//
//            /*
//             * convert string to array of bytes
//             */
//            final byte[] dec = Base64.decode(decryptStr);
//
//            /*
//             * create the cipher
//             */
//            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
//
//            /*
//             * decrypt
//             */
//            final byte[] plainText = new byte[cipher.getOutputSize(dec.length)];
//            int ptLength = cipher.update(dec, 0, dec.length, plainText, 0);
//            ptLength += cipher.doFinal(plainText, ptLength);
//            // System.out.println(new String(plainText));
//            // System.out.println(ptLength);
//
//            return new String(plainText).trim();
//        } catch (final Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }


    public static String decrypt(final String decryptStr) {
        try {
            if (decryptStr == null) {
                // System.err.println("[ERROR] Decryption string is null") ;
                return null;
            }

            /*
             * convert string to array of bytes
             */
            final byte[] dec = Base64.decode(decryptStr);

            /*
             * create the cipher
             */
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

            /*
             * decrypt
             */
            final byte[] plainText = new byte[cipher.getOutputSize(dec.length)];
            int ptLength = cipher.update(dec, 0, dec.length, plainText, 0);
            ptLength += cipher.doFinal(plainText, ptLength);
            // System.out.println(new String(plainText));
            // System.out.println(ptLength);

            return new String(plainText).trim();
        } catch (final Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static class Base64 {
        /**
         * Specify encoding (value is <tt>true</tt>).
         */
        public static final boolean ENCODE = true;

        /**
         * Specify decoding (value is <tt>false</tt>).
         */
        public static final boolean DECODE = false;

        /**
         * Specify that data should be compressed (value is <tt>true</tt>).
         */
        public static final boolean COMPRESS = true;

        /**
         * Specify that data should not be compressed (value is <tt>false</tt>).
         */
        public static final boolean DONT_COMPRESS = false;

        /**
         * Maximum line length (76) of Base64 output.
         */
        private static final int MAX_LINE_LENGTH = 76;

        /**
         * The equals sign (=) as a byte.
         */
        private static final byte EQUALS_SIGN = (byte) '=';

        /**
         * The new line character (\n) as a byte.
         */
        private static final byte NEW_LINE = (byte) '\n';

        /**
         * The 64 valid Base64 values.
         */
        private static final byte[] ALPHABET = { (byte) 'A', (byte) 'B', (byte) 'C', (byte) 'D', (byte) 'E', (byte) 'F', (byte) 'G', (byte) 'H', (byte) 'I',
                (byte) 'J', (byte) 'K', (byte) 'L', (byte) 'M', (byte) 'N', (byte) 'O', (byte) 'P', (byte) 'Q', (byte) 'R', (byte) 'S', (byte) 'T', (byte) 'U',
                (byte) 'V', (byte) 'W', (byte) 'X', (byte) 'Y', (byte) 'Z', (byte) 'a', (byte) 'b', (byte) 'c', (byte) 'd', (byte) 'e', (byte) 'f', (byte) 'g',
                (byte) 'h', (byte) 'i', (byte) 'j', (byte) 'k', (byte) 'l', (byte) 'm', (byte) 'n', (byte) 'o', (byte) 'p', (byte) 'q', (byte) 'r', (byte) 's',
                (byte) 't', (byte) 'u', (byte) 'v', (byte) 'w', (byte) 'x', (byte) 'y', (byte) 'z', (byte) '0', (byte) '1', (byte) '2', (byte) '3', (byte) '4',
                (byte) '5', (byte) '6', (byte) '7', (byte) '8', (byte) '9', (byte) '+', (byte) '/' };

        /**
         * Translates a Base64 value to either its 6-bit reconstruction value or a negative number indicating some other meaning.
         */
        private static final byte[] DECODABET = { -9, -9, -9, -9, -9, -9, -9, -9, -9, // Decimal 0 - 8
                -5, -5, // Whitespace: Tab and Linefeed
                -9, -9, // Decimal 11 - 12
                -5, // Whitespace: Carriage Return
                -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, // Decimal 14 - 26
                -9, -9, -9, -9, -9, // Decimal 27 - 31
                -5, // Whitespace: Space
                -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, // Decimal 33 - 42
                62, // Plus sign at decimal 43
                -9, -9, -9, // Decimal 44 - 46
                63, // Slash at decimal 47
                52, 53, 54, 55, 56, 57, 58, 59, 60, 61, // Numbers zero through nine
                -9, -9, -9, // Decimal 58 - 60
                -1, // Equals sign at decimal 61
                -9, -9, -9, // Decimal 62 - 64
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, // Letters 'A' through 'N'
                14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, // Letters 'O' through 'Z'
                -9, -9, -9, -9, -9, -9, // Decimal 91 - 96
                26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, // Letters 'a' through 'm'
                39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, // Letters 'n' through 'z'
                -9, -9, -9, -9 // Decimal 123 - 126
            /*
             * ,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 127 - 139 -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 140 - 152
             * -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 153 - 165 -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 166 - 178
             * -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 179 - 191 -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 192 - 204
             * -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 205 - 217 -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 218 - 230
             * -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9, // Decimal 231 - 243 -9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9,-9 // Decimal 244 - 255
             */
        };

        private static final byte WHITE_SPACE_ENC = -5; // Indicates white space in encoding
        private static final byte EQUALS_SIGN_ENC = -1; // Indicates equals sign in encoding

        /**
         * Defeats instantiation.
         */
        private Base64() {
        }

        /* ******** E N C O D I N G M E T H O D S ******** */

        /**
         * Encodes up to three bytes of the array <var>source</var> and writes the resulting four Base64 bytes to <var>destination</var>. The source and
         * destination arrays can be manipulated anywhere along their length by specifying <var>srcOffset</var> and <var>destOffset</var>. This method does not
         * check to make sure your arrays are large enough to accomodate <var>srcOffset</var> + 3 for the <var>source</var> array or <var>destOffset</var> + 4
         * for the <var>destination</var> array. The actual number of significant bytes in your array is given by <var>numSigBytes</var>.
         *
         * @param source
         *            the array to convert
         * @param srcOffset
         *            the index where conversion begins
         * @param numSigBytes
         *            the number of significant bytes in your array
         * @param destination
         *            the array to hold the conversion
         * @param destOffset
         *            the index where output will be put
         * @return the <var>destination</var> array
         * @since 1.3
         */
        private static byte[] encode3to4(final byte[] source, final int srcOffset, final int numSigBytes, final byte[] destination, final int destOffset) {
            // 1 2 3
            // 01234567890123456789012345678901 Bit position
            // --------000000001111111122222222 Array position from threeBytes
            // --------| || || || | Six bit groups to index ALPHABET
            // >>18 >>12 >> 6 >> 0 Right shift necessary
            // 0x3f 0x3f 0x3f Additional AND

            // Create buffer with zero-padding if there are only one or two
            // significant bytes passed in the array.
            // We have to shift left 24 in order to flush out the 1's that appear
            // when Java treats a value as negative that is cast from a byte to an int.
            final int inBuff = (numSigBytes > 0 ? ((source[srcOffset] << 24) >>> 8) : 0) | (numSigBytes > 1 ? ((source[srcOffset + 1] << 24) >>> 16) : 0)
                    | (numSigBytes > 2 ? ((source[srcOffset + 2] << 24) >>> 24) : 0);

            switch (numSigBytes) {
                case 3:
                    destination[destOffset] = ALPHABET[(inBuff >>> 18)];
                    destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 0x3f];
                    destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 0x3f];
                    destination[destOffset + 3] = ALPHABET[(inBuff) & 0x3f];
                    return destination;
                case 2:
                    destination[destOffset] = ALPHABET[(inBuff >>> 18)];
                    destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 0x3f];
                    destination[destOffset + 2] = ALPHABET[(inBuff >>> 6) & 0x3f];
                    destination[destOffset + 3] = EQUALS_SIGN;
                    return destination;
                case 1:
                    destination[destOffset] = ALPHABET[(inBuff >>> 18)];
                    destination[destOffset + 1] = ALPHABET[(inBuff >>> 12) & 0x3f];
                    destination[destOffset + 2] = EQUALS_SIGN;
                    destination[destOffset + 3] = EQUALS_SIGN;
                    return destination;
                default:
                    return destination;
            } // end switch
        } // end encode3to4

        /**
         * Encodes a byte array into Base64 notation. Equivalen to calling <code>encodeBytes( source, 0, source.length )</code>
         *
         * @param source
         *            The data to convert
         * @since 1.4
         */
        public static String encodeBytes(final byte[] source) {
            return encodeBytes(source, 0, source.length, true);
        } // end encodeBytes

        /**
         * Encodes a byte array into Base64 notation.
         *
         * @param source
         *            The data to convert
         * @param off
         *            Offset in array where conversion should begin
         * @param len
         *            Length of data to convert
         * @param breakLines
         *            Break lines at 80 characters or less.
         * @since 1.4
         */
        private static String encodeBytes(final byte[] source, final int off, final int len, final boolean breakLines) {
            final int len43 = len * 4 / 3;
            final byte[] outBuff = new byte[(len43) // Main 4:3
                    + ((len % 3) > 0 ? 4 : 0) // Account for padding
                    + (breakLines ? (len43 / MAX_LINE_LENGTH) : 0)]; // New lines
            int d = 0;
            int e = 0;
            final int len2 = len - 2;
            int lineLength = 0;
            for (; d < len2; d += 3, e += 4) {
                encode3to4(source, d + off, 3, outBuff, e);

                lineLength += 4;
                if (breakLines && lineLength == MAX_LINE_LENGTH) {
                    outBuff[e + 4] = NEW_LINE;
                    e++;
                    lineLength = 0;
                } // end if: end of line
            } // en dfor: each piece of array

            if (d < len) {
                encode3to4(source, d + off, len - d, outBuff, e);
                e += 4;
            } // end if: some padding needed

            return new String(outBuff, 0, e);
        } // end encodeBytes

        /* ******** D E C O D I N G M E T H O D S ******** */

        /**
         * Decodes four bytes from array <var>source</var> and writes the resulting bytes (up to three of them) to <var>destination</var>. The source and
         * destination arrays can be manipulated anywhere along their length by specifying <var>srcOffset</var> and <var>destOffset</var>. This method does not
         * check to make sure your arrays are large enough to accomodate <var>srcOffset</var> + 4 for the <var>source</var> array or <var>destOffset</var> + 3
         * for the <var>destination</var> array. This method returns the actual number of bytes that were converted from the Base64 encoding.
         *
         * @param source
         *            the array to convert
         * @param srcOffset
         *            the index where conversion begins
         * @param destination
         *            the array to hold the conversion
         * @param destOffset
         *            the index where output will be put
         * @return the number of decoded bytes converted
         * @since 1.3
         */
        private static int decode4to3(final byte[] source, final int srcOffset, final byte[] destination, final int destOffset) {
            // Example: Dk==
            if (source[srcOffset + 2] == EQUALS_SIGN) {
                // Two ways to do the same thing. Don't know which way I like best.
                // int outBuff = ( ( DECODABET[ source[ srcOffset ] ] << 24 ) >>> 6 )
                // | ( ( DECODABET[ source[ srcOffset + 1] ] << 24 ) >>> 12 );
                final int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18) | ((DECODABET[source[srcOffset + 1]] & 0xFF) << 12);

                destination[destOffset] = (byte) (outBuff >>> 16);
                return 1;
            } else if (source[srcOffset + 3] == EQUALS_SIGN) { // Example: DkL=
                // Two ways to do the same thing. Don't know which way I like best.
                // int outBuff = ( ( DECODABET[ source[ srcOffset ] ] << 24 ) >>> 6 )
                // | ( ( DECODABET[ source[ srcOffset + 1 ] ] << 24 ) >>> 12 )
                // | ( ( DECODABET[ source[ srcOffset + 2 ] ] << 24 ) >>> 18 );
                final int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18) | ((DECODABET[source[srcOffset + 1]] & 0xFF) << 12)
                        | ((DECODABET[source[srcOffset + 2]] & 0xFF) << 6);

                destination[destOffset] = (byte) (outBuff >>> 16);
                destination[destOffset + 1] = (byte) (outBuff >>> 8);
                return 2;
            } else { // Example: DkLE
                try {
                    // Two ways to do the same thing. Don't know which way I like best.
                    // int outBuff = ( ( DECODABET[ source[ srcOffset ] ] << 24 ) >>> 6 )
                    // | ( ( DECODABET[ source[ srcOffset + 1 ] ] << 24 ) >>> 12 )
                    // | ( ( DECODABET[ source[ srcOffset + 2 ] ] << 24 ) >>> 18 )
                    // | ( ( DECODABET[ source[ srcOffset + 3 ] ] << 24 ) >>> 24 );
                    final int outBuff = ((DECODABET[source[srcOffset]] & 0xFF) << 18) | ((DECODABET[source[srcOffset + 1]] & 0xFF) << 12)
                            | ((DECODABET[source[srcOffset + 2]] & 0xFF) << 6) | ((DECODABET[source[srcOffset + 3]] & 0xFF));

                    destination[destOffset] = (byte) (outBuff >> 16);
                    destination[destOffset + 1] = (byte) (outBuff >> 8);
                    destination[destOffset + 2] = (byte) (outBuff);

                    return 3;
                } catch (final Exception e) {
                    // System.out.println("" + source[srcOffset] + ": " + (DECODABET[source[srcOffset]]));
                    // System.out.println("" + source[srcOffset + 1] + ": " + (DECODABET[source[srcOffset + 1]]));
                    // System.out.println("" + source[srcOffset + 2] + ": " + (DECODABET[source[srcOffset + 2]]));
                    // System.out.println("" + source[srcOffset + 3] + ": " + (DECODABET[source[srcOffset + 3]]));
                    return -1;
                } // end catch
            }
        } // end decodeToBytes

        /**
         * Decodes data from Base64 notation.
         *
         * @param s
         *            the string to decode
         * @return the decoded data
         * @since 1.4
         */
        public static byte[] decode(final String s) {
            final byte[] bytes = s.getBytes();
            return decode(bytes, 0, bytes.length);
        } // end decode

        /**
         * Decodes Base64 content in byte array format and returns the decoded byte array.
         *
         * @param source
         *            The Base64 encoded data
         * @param off
         *            The offset of where to begin decoding
         * @param len
         *            The length of characters to decode
         * @return decoded data
         * @since 1.3
         */
        private static byte[] decode(final byte[] source, final int off, final int len) {
            final int len34 = len * 3 / 4;
            final byte[] outBuff = new byte[len34]; // Upper limit on size of output
            int outBuffPosn = 0;

            final byte[] b4 = new byte[4];
            int b4Posn = 0;
            int i = 0;
            byte sbiCrop = 0;
            byte sbiDecode = 0;
            for (i = off; i < off + len; i++) {
                sbiCrop = (byte) (source[i] & 0x7f); // Only the low seven bits
                sbiDecode = DECODABET[sbiCrop];

                if (sbiDecode >= WHITE_SPACE_ENC) // White space, Equals sign or better
                {
                    if (sbiDecode >= EQUALS_SIGN_ENC) {
                        b4[b4Posn++] = sbiCrop;
                        if (b4Posn > 3) {
                            outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn);
                            b4Posn = 0;

                            // If that was the equals sign, break out of 'for' loop
                            if (sbiCrop == EQUALS_SIGN) {
                                break;
                            }
                        } // end if: quartet built

                    } // end if: equals sign or better

                } else { // end if: white space, equals sign or better
                    // System.err.println("Bad Base64 input character at " + i + ": " + source[i] + "(decimal)");
                    return null;
                } // end else:
            } // each input character

            final byte[] out = new byte[outBuffPosn];
            System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
            return out;
        } // end decode

    } // end class Base64

    public static void thereback(String s) {
        ConfHelper confHelper = new ConfHelper();

        String encString = confHelper.encrypt(s);
        String decrString = confHelper.decrypt(encString);
        System.out.println(encString);
        System.out.println(decrString);
    }

    public static void main(final String[] args) throws ShortBufferException, InvalidAlgorithmParameterException {
        thereback("Passw0rd!");
        thereback("362044Dpa!");
        thereback(" Passw 0rd ");
        String s = "Hello";
        String s1 = " " + s;
        String s2 = s + " " + s;
        String s3 = s + " ";

        System.out.println(s.contains(" "));
        System.out.println(s1.contains(" "));
        System.out.println(s2.contains(" "));
        System.out.println(s3.contains(" "));
        //        System.out.println(confHelper.get2("Zkn6iucEmYWjB+TcqDxD3Byw5PwQUFbF+mwqsZTMVAk\\="));

    }
}
