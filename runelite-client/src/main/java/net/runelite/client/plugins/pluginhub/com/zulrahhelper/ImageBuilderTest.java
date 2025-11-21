package net.runelite.client.plugins.pluginhub.com.zulrahhelper;

import net.runelite.client.plugins.pluginhub.com.zulrahhelper.options.Prayer;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.options.StandLocation;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.options.ZulrahForm;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.options.ZulrahLocation;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.tree.Node;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.tree.Step;
import net.runelite.client.plugins.pluginhub.com.zulrahhelper.ui.Images;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static org.mockito.Mockito.spy;

@Slf4j
public class ImageBuilderTest
{
	private ZulrahHelperConfig getConfig()
	{
		return spy(ZulrahHelperConfig.class);
	}

	@Test
	public void streamlinedBuilder()
	{
		var root = Node.builder()
			.value(Step.builder()
				.point(StandLocation.START)
				.form(ZulrahForm.RANGE)
				.attacks(5)
				.venom(4)
				.build())
			.node()
			.value(Step.builder()
				.form(ZulrahForm.MELEE)
				.point(StandLocation.START)
				.point(StandLocation.START_MAGMA)
				.attacks(2)
				.build())
			.node()
			.value(Step.builder()
				.form(ZulrahForm.MAGE)
				.point(StandLocation.START)
				.point(StandLocation.START_MAGMA)
				.attacks(4)
				.build())
			.buildUp();

		assertEquals(1, root.getChildren().size());
		assertEquals(root, root.getChildren().get(0).getParent());
		assertEquals(1, root.getChildren().get(0).getChildren().size());
		assertEquals(ZulrahForm.MAGE, root.getChildren().get(0).getChildren().get(0).getValue().getForm());
	}

	@Test
	public void childrenHasParent()
	{
		var root = Node.builder()
			.value(Step.builder()
				.point(StandLocation.START)
				.form(ZulrahForm.RANGE)
				.attacks(5)
				.venom(4)
				.build())
			.child(Node.builder()
				.value(Step.builder()
					.form(ZulrahForm.MELEE)
					.point(StandLocation.START)
					.point(StandLocation.START_MAGMA)
					.attacks(2)
					.build())
				.build())
			.build();

		assertEquals(1, root.getChildren().size());
		assertEquals(root, root.getChildren().get(0).getParent());
		assertEquals(0, root.getChildren().get(0).getChildren().size());
	}

	@Test
	public void simpleStep() throws IOException
	{
		Step step = Step.builder()
			.point(StandLocation.START)
			.point(StandLocation.START_MAGMA)
			.spawn(ZulrahLocation.NORTH)
			.prayer(Prayer.MAGIC)
			.prayer(Prayer.RANGE)
			.form(ZulrahForm.MELEE)
			.snakelings(2)
			.attacks(6)
			.venom(3)
			.build();

		assertEquals(StandLocation.START, step.getPoints().get(0));
		assertEquals(ZulrahLocation.NORTH, step.getSpawn());
		assertEquals(2, step.getPrayers().size());
		assertEquals(Prayer.MAGIC, step.getPrayers().get(0));

		BufferedImage img = Images.createImage(step, getConfig());

		File out = new File("out/simple.png");
		ImageIO.write(img, "png", out);
	}

	@Test
	public void spawns() throws IOException
	{
		for (var loc : ZulrahLocation.values())
		{
			Step step = Step.builder()
				.point(StandLocation.START)
				.spawn(loc)
				.build();

			BufferedImage img = Images.createImage(step, getConfig());

			File out = new File("out/spawns/" + loc + ".png");
			ImageIO.write(img, "png", out);
		}
	}

	@Test
	public void points() throws IOException
	{
		for (var loc : StandLocation.values())
		{
			Step step = Step.builder()
				.point(loc)
				.prayer(Prayer.MAGIC)
				.build();

			BufferedImage img = Images.createImage(step, getConfig());

			File out = new File("out/points/" + loc + ".png");
			ImageIO.write(img, "png", out);
		}
	}
}

