package net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.exceptions;

import net.runelite.client.plugins.pluginhub.com.duckblade.osrs.dpscalc.calc.compute.ComputeInputs;

public class MissingInputException extends RuntimeException
{

	public MissingInputException(ComputeInputs<?> missingInput)
	{
		super(String.format("ComputeInput [%s] was not supplied and has no default value.", missingInput.key()));
	}

}
