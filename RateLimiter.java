// Rate limiter example
// 2026-02-27

﻿import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 40min exercise (improvised unit tests via stdout)
class RateLimiter {
    public static void main(String[] args) {
        System.out.println("boolean[] rateLimiter(int[] requestTimes, int maxInWindow, int windowLength)");

        System.out.print("[], 2, 4 --> ");
        System.out.println("[] <> " + Arrays.toString(rateLimiter(new int[] {}, 2, 4)));

        System.out.print("[1], 2, 4 --> ");
        System.out.println("[true] <> " + Arrays.toString(rateLimiter(new int[] {1}, 2, 4)));

        System.out.print("[1, 3, 4, 6], 2, 4 --> ");
        System.out.println("[true, true, false, true] <> " + Arrays.toString(rateLimiter(new int[] {1, 3, 4, 6}, 2, 4 )));

        System.out.print("[1, 3, 5, 6], 2, 4 --> ");
        System.out.println("[true, true, true, false] <> " + Arrays.toString(rateLimiter(new int[] {1, 3, 5, 6}, 2, 4 )));
    }

    public static boolean[] rateLimiter(int[] requestTimes, int maxInWindow, int windowLength) {
        boolean[] allowed = new boolean[requestTimes.length];
        Deque window = new LinkedList<Integer>();
        // optimization for times when requests.length<maxInWindow, 
        // maxInWindow<=windowLength
        
        for (int i = 0; i < requestTimes.length; i++) {
            int currentTime = requestTimes[i];
            int outOfWindow = Math.max(0, currentTime - windowLength - 1);
            
            while (window.size() > 0 && (int) window.peekFirst() < outOfWindow) {
                window.pollFirst(); 
            }
            if (window.size() < maxInWindow) {
                window.addLast(requestTimes[i]);
                allowed[i] = true;
            }
        }
        return allowed;
    }
}
