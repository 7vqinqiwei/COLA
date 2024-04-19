package com.alibaba.cola.biz.context;


import org.slf4j.MDC;

import java.util.Random;


/**
 * 链路上下文
 *
 * @author qi.wei
 * @date 2023/10/21 1:10
 */
public class TraceContext {

    public static final String HTTP_TRACE_HEAD = "B-TRACE-ID";
    public static final String KAFKA_TRACE_HEAD = "K-TRACE-ID";
    public static final String TRACE_ID = "PtxId";
    public static final String SPAN_ID = "span_id";

    public static String get() {
        return MDC.get(TRACE_ID);
    }

    public static void generateTraceId() {
        MDC.put(TRACE_ID, random(20));
    }

    public static void set(String traceId) {
        MDC.put(TRACE_ID, traceId);
    }

    public static void remove() {
        MDC.remove(TRACE_ID);
    }

    public static String getSpanId() {
        return MDC.get(SPAN_ID);
    }


    public static String random(int count) {
        Random random = new Random();
        int start = 0, end = 0;
        boolean letters = true, numbers = true;

        if (count == 0) {
            return "";
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        } else {
            if (start == 0 && end == 0) {
                end = 123;
                start = 32;
                if (!letters && !numbers) {
                    start = 0;
                    end = 2147483647;
                }
            }

            char[] buffer = new char[count];
            int gap = end - start;

            while (true) {
                while (true) {
                    while (count-- != 0) {
                        char ch;
                        ch = (char) (random.nextInt(gap) + start);
                        if (letters && Character.isLetter(ch) || numbers && Character.isDigit(ch) || !letters && !numbers) {
                            if (ch >= '\udc00' && ch <= '\udfff') {
                                if (count == 0) {
                                    ++count;
                                } else {
                                    buffer[count] = ch;
                                    --count;
                                    buffer[count] = (char) ('\ud800' + random.nextInt(128));
                                }
                            } else if (ch >= '\ud800' && ch <= '\udb7f') {
                                if (count == 0) {
                                    ++count;
                                } else {
                                    buffer[count] = (char) ('\udc00' + random.nextInt(128));
                                    --count;
                                    buffer[count] = ch;
                                }
                            } else if (ch >= '\udb80' && ch <= '\udbff') {
                                ++count;
                            } else {
                                buffer[count] = ch;
                            }
                        } else {
                            ++count;
                        }
                    }

                    return new String(buffer);
                }
            }
        }
    }
}
