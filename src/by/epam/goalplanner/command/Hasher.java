package by.epam.goalplanner.command;

import by.epam.goalplanner.exception.CommandException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    public static String getHash(String password) throws CommandException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = digest.digest(password.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b: bytes) {
                builder.append(String.format("%02X ", b));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CommandException(e);
        }
    }
}
