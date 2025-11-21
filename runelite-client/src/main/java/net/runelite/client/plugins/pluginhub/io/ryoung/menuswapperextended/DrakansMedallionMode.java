package net.runelite.client.plugins.pluginhub.io.ryoung.menuswapperextended;

import java.util.function.Predicate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DrakansMedallionMode implements SwapMode
{
	WEAR("Wear"),
	VER_SINHAZA("Ver Sinhaza"),
	DARKMEYER("Darkmeyer"),
	SLEPE("Slepe");

	private final String option;

	@Override
	public String toString()
	{
		return option;
	}

	@Override
	public boolean checkShift()
	{
		return true;
	}

	@Override
	public Predicate<String> checkTarget()
	{
		return target -> target.startsWith("drakan's medallion");
	}
}

