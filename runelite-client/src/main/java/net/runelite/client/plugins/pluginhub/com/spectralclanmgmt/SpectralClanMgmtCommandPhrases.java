package net.runelite.client.plugins.pluginhub.com.spectralclanmgmt;

/*
Explanation behind this class:

This is to hold the values for the random phrases that could be said when the !spectral command is issued.
This command, like the other Spectral commands, won't work outside of the clan chat channel. 
If it's used in any other channel, or by anyone that's not a ranked member of Spectral, 
the command will be consumed so it won't be displayed in the chat.

Permission checks are done via asynchronous http requests to Spectral's web app when spectral's commands are used 
to verify that the player, or the clan as a whole, is allowed to use any of spectral's commands. 
In the instance that a clan member's permissions to use one or all of spectral's commands has been revoked, 
the command will be consumed. If the command isn't able to be used by a ranked clan member for any reason, 
such as when their permissions have been revoked, a chat message will be added to the game chat explaining why.
*/

public class SpectralClanMgmtCommandPhrases
{
	/* 
	Values for this string array only come from Spectral's private spreadsheet that only the clan's moderators can edit.
	All values on the phrases spreadsheet are verified by the clan's mods and leaders to adhere to OSRS's rules and Jagex's T&C.
	All values on the phrases spreadsheet are verified to not exceed the chat box's input limit before being added to the sheet. 
	The mods and leaders verify that the phrases don't contain links, directions to links, or anything that qualifies 
	as illegal or rule-breaking content as outlined in the Safety and Abuse section, and the User Content section, of Jagex's T&C.
	 */
	
	private String[] phrases;
	
	protected SpectralClanMgmtCommandPhrases()
	{
		this.phrases = null;
	}
	
	protected String[] getPhrases()
	{
		return this.phrases;
	}
	
	protected void setPhrases(String[] phrases)
	{
		this.phrases = phrases;
	}
}

/*
 * Copyright (c) 2018 Abex
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */