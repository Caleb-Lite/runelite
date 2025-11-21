package net.runelite.client.plugins.pluginhub.com.collectionlogmaster.synchronization;

import net.runelite.client.plugins.pluginhub.com.collectionlogmaster.domain.Task;
import lombok.NonNull;

public interface Verifier {
    boolean supports(@NonNull Task task);
    boolean verify(@NonNull Task task);
}
