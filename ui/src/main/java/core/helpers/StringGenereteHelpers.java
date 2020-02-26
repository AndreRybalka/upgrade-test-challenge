package core.helpers;

import java.security.SecureRandom;

import static constants.Constants.CONSTANT_STRING_FOR_EMAIL_GENERATION;

public class StringGenereteHelpers {
    private static SecureRandom randomFill = new SecureRandom();

    public static String generateRandomEmailLocalPart(int length) {
        StringBuilder randomFillSignUpFieldsInfoGenerated = new StringBuilder(length);
        generateRandomStringMethod(length, randomFillSignUpFieldsInfoGenerated);
        return randomFillSignUpFieldsInfoGenerated.toString();
    }

    private static void generateRandomStringMethod(int length, StringBuilder stringBuilder) {
        for (int i = 0; i < length; i++)
            stringBuilder.append(CONSTANT_STRING_FOR_EMAIL_GENERATION
                    .charAt(randomFill.nextInt(CONSTANT_STRING_FOR_EMAIL_GENERATION.length())));
    }
}
