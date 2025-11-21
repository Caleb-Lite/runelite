package net.runelite.client.plugins.pluginhub.com.andmcadams.taskchecker.tasklist;

import net.runelite.client.plugins.pluginhub.com.andmcadams.taskchecker.Task;
import net.runelite.client.plugins.pluginhub.com.andmcadams.taskchecker.Varbits;

public class ChampionsChallengeTaskList extends TaskList
{

	public ChampionsChallengeTaskList()
	{
		super("Champions' Challenge");
		initTasks();
	}

	public void initTasks()
	{

		// Earth warrior, ghoul, hill giant, goblin, hobgoblin, imp, jogre, lesser demon, skeleton, zombie
		Task earthWarriorLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the earth warrior champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 0)
			.build();

		Task ghoulLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the ghoul champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 1)
			.build();

		Task giantLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the giant champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 2)
			.build();

		Task goblinLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the goblin champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 3)
			.build();

		Task hobgoblinLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the hobgoblin champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 4)
			.build();

		Task impLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the imp champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 5)
			.build();

		Task jogreLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the jogre champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 6)
			.build();

		Task lesserDemonLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the lesser demon champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 7)
			.build();

		Task skeletonLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the skeleton champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 8)
			.build();

		Task zombieLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the zombie champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 9)
			.build();

		Task humanLampUsed = new Task.TaskBuilder()
			.name("Use the champion's lamp of the human champion")
			.bitVar(true, Varbits.CHAMPIONS_CHALLENGE_LAMPS_BITMAP.getId(), 10)
			.build();

		add(earthWarriorLampUsed);
		add(ghoulLampUsed);
		add(giantLampUsed);
		add(goblinLampUsed);
		add(hobgoblinLampUsed);
		add(impLampUsed);
		add(jogreLampUsed);
		add(lesserDemonLampUsed);
		add(skeletonLampUsed);
		add(zombieLampUsed);
		add(humanLampUsed);
	}
}

/*
 * Copyright (c) 2021, Andrew McAdams
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