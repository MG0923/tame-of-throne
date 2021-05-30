package com.geektrust;

import com.geektrust.enums.KingdomEmblem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * //65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90
 * //A  B  C  D  E  F  G  H  I  J  K   L  M  N  O  P  Q  R  S  T  U  V  W  X  Y  Z
 * */
public class GeekTrust {
    public static boolean decrypt(String[] arr) {
        try {
            String emblem = KingdomEmblem.valueOf(arr[0]).getEmblem();
            String encryptedMessage = arr[1];
            Map<Character, Integer> map = generateEmblemMap(emblem);

            for (int i = 0; i <= encryptedMessage.length() / 2; i++) {
                char key = encryptedMessage.charAt(i);
                if (map.containsKey(key)) {
                    Integer count = map.get(key);
                    if (count > 1) {
                        map.put(key, count - 1);
                    } else {
                        map.remove(key);
                    }
                }
                key = encryptedMessage.charAt(encryptedMessage.length() - 1 - i);
                if (map.containsKey(key)) {
                    Integer count = map.get(key);
                    if (count > 1) {
                        map.put(key, count - 1);
                    } else {
                        map.remove(key);
                    }
                }
                if (map.isEmpty())
                    return true;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
        return false;
    }

    public static Map<Character, Integer> generateEmblemMap(String emblem) {
        Map<Character, Integer> map = new HashMap<>();
        int length = emblem.length();
        for (int i = 0; i < length; i++) {
            char key;
            if (emblem.charAt(i) + length > 90) {
                key = (char) (emblem.charAt(i) + length - 26);
            } else {
                key = (char) (emblem.charAt(i) + length);
            }
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        return map;
    }

    public static void main(String[] args) throws IOException {
        int count = 0;
        String filePath = args[0];
        List<String> allies = new ArrayList<>();
        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder res;
        String st;

        while ((st = br.readLine()) != null) {
            String[] arr = st.split(" ", 2);
            if (arr.length < 2)
                continue;
            boolean ally = decrypt(arr);
            if (ally) {
                count++;
                allies.add(arr[0]);
            }
            if (count == 3) {
                break;
            }
        }

        br.close();

        if (allies.size() >= 3) {
            res = new StringBuilder("SPACE");
            for (String s : allies) {
                res.append(" ").append(s);
            }
        } else {
            res = new StringBuilder("NONE");
        }
        System.out.println(res);
    }
}
