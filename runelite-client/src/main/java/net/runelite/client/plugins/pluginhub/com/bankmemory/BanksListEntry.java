package net.runelite.client.plugins.pluginhub.com.bankmemory;

import net.runelite.client.plugins.pluginhub.com.bankmemory.data.BankWorldType;
import javax.swing.ImageIcon;
import lombok.Value;

@Value
public class BanksListEntry {
    long saveId;
    ImageIcon icon;
    BankWorldType worldType;
    String saveName;
    String accountDisplayName;
    String dateTime;
}
