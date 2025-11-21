package net.runelite.client.plugins.pluginhub.com.github.nicDamours.Exceptions;

public class InvalidContainerException extends Exception{
    public InvalidContainerException() {
    }

    @Override
    public String getMessage() {
        return "Container is not POH treasure chest.";
    }
}

