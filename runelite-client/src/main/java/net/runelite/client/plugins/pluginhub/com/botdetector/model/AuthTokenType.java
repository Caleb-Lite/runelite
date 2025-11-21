package net.runelite.client.plugins.pluginhub.com.botdetector.model;

import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import static com.botdetector.model.AuthTokenPermission.*;

@Getter
@RequiredArgsConstructor
public enum AuthTokenType
{
	/**
	 * No permissions
	 */
	NONE(ImmutableSet.of()),

	/**
	 * All permissions
	 */
	DEV(Arrays.stream(AuthTokenPermission.values()).collect(ImmutableSet.toImmutableSet())),

	/**
	 * Can perform discord verification
	 */
	MOD(ImmutableSet.of(VERIFY_DISCORD))
	;

	private final ImmutableSet<AuthTokenPermission> permissions;

	/**
	 * Parses the token type from the given {@code prefix}.
	 * @param prefix The prefix to parse.
	 * @return The token type if parsed successfully, {@link #NONE} otherwise.
	 */
	public static AuthTokenType fromPrefix(String prefix)
	{
		if (prefix == null)
		{
			return AuthTokenType.NONE;
		}

		try
		{
			return AuthTokenType.valueOf(prefix.toUpperCase());
		}
		catch (IllegalArgumentException e)
		{
			return AuthTokenType.NONE;
		}
	}
}
