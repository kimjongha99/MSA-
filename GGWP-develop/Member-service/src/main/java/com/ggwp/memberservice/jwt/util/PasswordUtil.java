package com.ggwp.memberservice.jwt.util;

import java.security.SecureRandom;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PasswordUtil {
//    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
//    private static final int PASSWORD_LENGTH = 10;
//    private static final SecureRandom random = new SecureRandom();
//
//    public static String generateRandomPassword() {
//        return IntStream.range(0, PASSWORD_LENGTH)
//                .map(i -> CHARACTERS.charAt(random.nextInt(CHARACTERS.length())))
//                .mapToObj(Character::toString)
//                .collect(Collectors.joining());
//
//
//    }
public static String generateRandomPassword() {
    int index = 0;
    char[] charSet = new char[] {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };	//배열안의 문자 숫자는 원하는대로

    StringBuffer password = new StringBuffer();
    Random random = new Random();

    for (int i = 0; i < 8 ; i++) {
        double rd = random.nextDouble();
        index = (int) (charSet.length * rd);

        password.append(charSet[index]);
    }
    System.out.println(password);
    return password.toString();
    //StringBuffer를 String으로 변환해서 return 하려면 toString()을 사용하면 된다.
}
}

