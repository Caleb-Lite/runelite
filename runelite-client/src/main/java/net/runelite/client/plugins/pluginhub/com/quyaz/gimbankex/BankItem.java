package net.runelite.client.plugins.pluginhub.com.quyaz.gimbankex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class BankItem {
    int itemId;
    @Setter
    int amount;
    String item;
}
