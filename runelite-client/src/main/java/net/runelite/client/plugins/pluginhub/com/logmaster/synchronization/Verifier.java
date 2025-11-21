package net.runelite.client.plugins.pluginhub.com.logmaster.synchronization;

import net.runelite.client.plugins.pluginhub.com.logmaster.domain.Task;
import lombok.NonNull;

public interface Verifier {
    boolean supports(@NonNull Task task);
    boolean verify(@NonNull Task task);
}
