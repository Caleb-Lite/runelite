package net.runelite.client.plugins.pluginhub.com.evansloan.collectionlog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSettings
{
	private AccountType displayRank = AccountType.ALL;
	private boolean showQuantity = true;
}
