package net.runelite.client.plugins.pluginhub.com.Crowdsourcing.mlm;

import com.google.common.collect.Multiset;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MLMData
{
	private final ArrayList<PaydirtMineData> paydirtMineData;
	private final Multiset<Integer> rewards;
	private final int currentSackCount;
	private final int paydirtCount;
}