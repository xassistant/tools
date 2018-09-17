
package com.alibaba.druid.sql.parser;

import static com.alibaba.druid.sql.parser.LayoutCharacters.EOI;

public class CharTypes {

    private final static boolean[] hexFlags = new boolean[256];

    static {
        for (char c = 0; c < hexFlags.length; ++c) {
            if (c >= 'A' && c <= 'F') {
                hexFlags[c] = true;
            } else if (c >= 'a' && c <= 'f') {
                hexFlags[c] = true;
            } else if (c >= '0' && c <= '9') {
                hexFlags[c] = true;
            }
        }
    }

    public static boolean isHex(char c) {
        return c < 256 && hexFlags[c];
    }

    public static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private final static boolean[] firstIdentifierFlags = new boolean[256];

    static {
        for (char c = 0; c < firstIdentifierFlags.length; ++c) {
            if (c >= 'A' && c <= 'Z') {
                firstIdentifierFlags[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                firstIdentifierFlags[c] = true;
            }
        }
        firstIdentifierFlags['`'] = true;
        firstIdentifierFlags['_'] = true;
        firstIdentifierFlags['$'] = true;
    }


    private final static String[] stringCache = new String[256];
    private final static boolean[] identifierFlags = new boolean[256];

    static {
        for (char c = 0; c < identifierFlags.length; ++c) {
            if (c >= 'A' && c <= 'Z') {
                identifierFlags[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                identifierFlags[c] = true;
            } else if (c >= '0' && c <= '9') {
                identifierFlags[c] = true;
            }
        }
        // identifierFlags['`'] = true;
        identifierFlags['_'] = true;
        identifierFlags['$'] = true;
        identifierFlags['#'] = true;

        for (int i = 0; i < identifierFlags.length; i++) {
            if (identifierFlags[i]) {
                char ch = (char) i;
                stringCache[i] = Character.toString(ch);
            }
        }
    }


    public static String valueOf(char ch) {
        if (ch < stringCache.length) {
            return stringCache[ch];
        }
        return null;
    }

    private final static boolean[] whitespaceFlags = new boolean[256];

    static {
        for (int i = 0; i <= 32; ++i) {
            whitespaceFlags[i] = true;
        }

        whitespaceFlags[EOI] = false;
        for (int i = 0x7F; i <= 0xA0; ++i) {
            whitespaceFlags[i] = true;
        }

        whitespaceFlags[160] = true; // 特别处理
    }


    public static boolean isWhitespace(char ch) {
        return false;
    }
}
