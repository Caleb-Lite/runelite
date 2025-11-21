package net.runelite.client.plugins.pluginhub.com.queuehelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import net.runelite.client.ui.FontManager;
import net.runelite.client.util.ImageUtil;

	class BasQueueRow extends JPanel
{
	private final JMenuItem addMenuOption = new JMenuItem();

	private boolean otherimg;

	private JLabel nameField;
	private JLabel idField;
	private JLabel itemField;
	private JTextArea notesField;

	public Customer customer;

	private Color lastBackground;

	private BASPlugin plugin;

	private JLabel item;


	BasQueueRow(Customer Customer, BASPlugin Plugin)
	{
		this.otherimg = false;
		this.customer = Customer;
		this.plugin = Plugin;

		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(2, 0, 2, 0));
		final JPopupMenu popupMenu = new JPopupMenu();
		popupMenu.setBorder(new EmptyBorder(5, 5, 5, 5));
		String menuText;
		int option;
		String tooltipHover = "";

		if(customer.getStatus().equals("")){
			menuText = "Mark " + customer.getName()+ " online";
			option = 3;
			tooltipHover = "Offline";
		}
		else if(customer.getNotes().toLowerCase().contains("cooldown")){
			menuText = "End Cooldown for: " + customer.getName()+ "(Currently unavailable)";
			option = 0;
			tooltipHover = "Cooldown";
		}
		else if(customer.getStatus().equals("In Progress")){
			if(customer.getItem().equals("Level 5 Roles") && (customer.getNotes().contains("d started") || customer.getNotes().contains("2/3"))){
				menuText = "Mark " + customer.getName()+ " done";
				option = 2;
				tooltipHover = "In Progress last session lvl5s";
			}
			else if(customer.getItem().equals("Level 5 Roles")){
				menuText = "Start Cooldown for: " + customer.getName();
				option = 4;
				tooltipHover = "In Progress lvl5s";
			}
			else{
				menuText = "Mark " + customer.getName()+ " done";
				option = 2;
				tooltipHover = "In Progress";
			}
		}
		else if(customer.getStatus().equals("Done"))
		{
			menuText = "Mark " + customer.getName()+ " in progress";
			option = 1;
			tooltipHover = "Done";
		}
		else{
			menuText = "Mark " + customer.getName()+ " in progress";
			option = 1;
			tooltipHover = "Online";
		}

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent mouseEvent)
			{
				if (mouseEvent.getClickCount() == 2)
				{
					plugin.markCustomer(option,customer);
				}
			}

			@Override
			public void mousePressed(MouseEvent mouseEvent)
			{
				if (mouseEvent.getClickCount() == 2)
				{
					setBackground(getBackground().brighter());
				}
			}

			@Override
			public void mouseReleased(MouseEvent mouseEvent)
			{
				if (mouseEvent.getClickCount() == 2)
				{
					setBackground(getBackground().darker());
				}
			}

			@Override
			public void mouseEntered(MouseEvent mouseEvent)
			{
				BasQueueRow.this.lastBackground = getBackground();
				setBackground(getBackground().brighter());
			}

			@Override
			public void mouseExited(MouseEvent mouseEvent)
			{
				setBackground(lastBackground);
			}
		});




		addMenuOption.setText(menuText);

		for (ActionListener listener : addMenuOption.getActionListeners())
		{
			addMenuOption.removeActionListener(listener);
		}

		addMenuOption.addActionListener(e ->
		{
			this.plugin.markCustomer(option,customer);
		});
		popupMenu.add(addMenuOption);

		setComponentPopupMenu(popupMenu);

		JPanel leftSide = new JPanel(new BorderLayout());
		JPanel rightSide = new JPanel(new BorderLayout());
		leftSide.setOpaque(false);
		rightSide.setOpaque(false);

		JPanel nameField = buildNameField(Customer);
		nameField.setPreferredSize(new Dimension(70, 10));
		nameField.setOpaque(false);

		JPanel idField = buildidField(Customer);
		idField.setPreferredSize(new Dimension(30, 10));
		idField.setOpaque(false);

		JPanel itemField = builditemField(Customer);
		itemField.setPreferredSize(new Dimension(30, 30));
		itemField.setOpaque(false);

		JPanel notesField = buildNotesField(Customer);
		notesField.setPreferredSize(new Dimension(5, 34));
		notesField.setOpaque(false);

		recolour(Customer);

		leftSide.add(idField, BorderLayout.WEST);
		leftSide.add(nameField, BorderLayout.CENTER);
		rightSide.add(itemField, BorderLayout.WEST);
		rightSide.add(notesField, BorderLayout.CENTER);
		add(leftSide, BorderLayout.WEST);
		add(rightSide, BorderLayout.CENTER);
		this.setToolTipText(tooltipHover);

	}


	public void recolour(Customer customer)
	{
		String status = customer.getStatus();
		Color curColor = Color.black;
		if(this.item == null)
		{
			itemField.setForeground(curColor);
		}
		notesField.setForeground(curColor);
		nameField.setForeground(curColor);
		idField.setForeground(curColor);
		switch(status)
		{
			case "Online":
				if(!customer.getNotes().toLowerCase().contains("cd")&&!customer.getNotes().toLowerCase().contains("cooldown"))
				{
					curColor = Color.green;
					if(this.item == null)
					{
						itemField.setForeground(curColor);
					}
					notesField.setForeground(curColor);
					nameField.setForeground(curColor);
					idField.setForeground(curColor);

					break;
				}
				curColor = new Color(99,151,255);
				this.setBackground(curColor);
				notesField.setBackground(curColor);
				break;

			case "In Progress":
				curColor = new Color(241,235,118);;
				this.setBackground(curColor);
				notesField.setBackground(curColor);
				break;

			case "Done":
				curColor = new Color(129,129,129);;
				this.setBackground(curColor);
				notesField.setBackground(curColor);
				break;

			case "":
				if(!customer.getNotes().toLowerCase().contains("cd")&&!customer.getNotes().toLowerCase().contains("cooldown"))
				{
					curColor = Color.red;
					if(this.item == null)
					{
						itemField.setForeground(curColor);
					}
					notesField.setForeground(curColor);
					nameField.setForeground(curColor);
					idField.setForeground(curColor);
					break;
				}
				curColor = new Color(99,151,255);
				this.setBackground(curColor);
				break;

			default:
				curColor = Color.gray;
				this.setBackground(curColor);

				break;
		}
	}

	/**
	 * Builds the players list field (containing the amount of players logged in that world).
	 */
	private JPanel builditemField(Customer cust)
	{
		String item = cust.getItem();
		JPanel column = new JPanel(new BorderLayout());
		switch(item){

			case "Torso":
				column = new JPanel(new BorderLayout());
				itemField = new JLabel(new ImageIcon(ImageUtil.loadImageResource(getClass(), "/torso.png")));
				column.add(itemField);
				return column;

			case "Queen Kill - Diary":
				column = new JPanel(new BorderLayout());
				itemField = new JLabel(new ImageIcon(ImageUtil.loadImageResource(getClass(), "/queen_kill.png")));
				column.add(itemField);
				return column;

			case "Level 5 Roles":
				column = new JPanel(new BorderLayout());
				itemField = new JLabel(new ImageIcon(ImageUtil.loadImageResource(getClass(), "/Level5s.png")));
				column.add(itemField);
				return column;

			case "Hat":
				column = new JPanel(new BorderLayout());
				itemField = new JLabel(new ImageIcon(ImageUtil.loadImageResource(getClass(), "/hat4.png")));
				column.add(itemField);
				return column;


			default:
				if(item.toLowerCase().contains("gamble")){
					column = new JPanel(new BorderLayout());
					itemField = new JLabel(new ImageIcon(ImageUtil.loadImageResource(getClass(), "/Other.png")));
					column.add(itemField);
					return column;

				}
				if(item.toLowerCase().contains("points") || item.toLowerCase().contains("pts")){
					column = new JPanel(new BorderLayout());
					itemField = new JLabel(new ImageIcon(ImageUtil.loadImageResource(getClass(), "/points.png")));
					otherimg = true;
					column.add(itemField);
					return column;

				}
				else
				{
					column = new JPanel(new BorderLayout());
					itemField = new JLabel(new ImageIcon(ImageUtil.loadImageResource(getClass(), "/Other.png")));
					column.add(itemField);
					otherimg = true;
					return column;
				}

		}
	}



	private JPanel buildidField(Customer cust)
	{
		JPanel column = new JPanel(new BorderLayout());
		column.setBorder(new EmptyBorder(0, 0, 0, 5));

		idField = new JLabel(cust.getID());
		idField.setFont(FontManager.getRunescapeSmallFont().deriveFont(8));

		idField.setToolTipText(cust.getID());
		// Pass up events - https://stackoverflow.com/a/14932443
		idField.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				dispatchEvent(e);
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				dispatchEvent(e);
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				dispatchEvent(e);
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				dispatchEvent(e);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				dispatchEvent(e);
			}
		});

		column.add(idField, BorderLayout.EAST);


		return column;
	}

	/**
	 * Builds the activity list field (containing that world's activity/theme).
	 */
	private JPanel buildNotesField(Customer cust)
	{
		JPanel column = new JPanel(new BorderLayout());
		column.setBorder(new EmptyBorder(0, 5, 0, 5));


		String activity = cust.getNotes();
		if(this.otherimg){
			activity = cust.getItem() + " " + cust.getNotes();
		}

		notesField = new JTextArea(2, 10);
		notesField.setText(activity);


		notesField.setLineWrap(true);
		notesField.setWrapStyleWord(true);
		notesField.setEditable(true);
		notesField.setOpaque(false);
		notesField.setFont(FontManager.getRunescapeSmallFont().deriveFont(this.plugin.fontSize));


		column.add(notesField, BorderLayout.WEST);

		return column;
	}


	private JPanel buildNameField(Customer cust)
	{
		JPanel column = new JPanel(new BorderLayout());
		column.setBorder(new EmptyBorder(0, 5, 0, 5));

		nameField = new JLabel(cust.getName());
		nameField.setFont(FontManager.getRunescapeSmallFont());

		column.add(nameField, BorderLayout.CENTER);

		return column;
	}






}

	/*
	 * Copyright (c) 2019, SkylerPIlot <https://github.com/SkylerPIlot>
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
	package com.queuehelper;
	import okhttp3.FormBody;
	import java.time.ZoneOffset;
	import java.time.ZonedDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.List;
	import java.util.ArrayList;
	import java.io.IOException;
	import net.runelite.api.events.ChatMessage;
	import okhttp3.Call;
	import okhttp3.Callback;
	import okhttp3.HttpUrl;
	import okhttp3.OkHttpClient;
	import okhttp3.Request;
	import okhttp3.Response;

	/**
	 This Class handles all IO communication to the backend
	 */

	public class BASHTTPClient implements QueueHelperHTTPClient
	{
		private static BASHTTPClient client;

		private String apikey;

		private static final String HOST_PATH = "vrqgs27251.execute-api.eu-west-2.amazonaws.com";

		private HttpUrl apiBase;

		private OkHttpClient Basclient;

		private List<String[]> csvData;
		private String RetrieveCSVQuery;




		private BASHTTPClient(String apikey, OkHttpClient basclient) throws IOException
		{
			this.Basclient = basclient;
			this.apikey = apikey;
			this.apiBase = new HttpUrl.Builder().scheme("https").host(BASHTTPClient.HOST_PATH).addPathSegment("Bas_Queuehelper").build();
			this.getFilePaths();
			//String[] pathsArray = this.getFilePaths();
			//this.updateFilePaths(pathsArray);
		}

		public static BASHTTPClient getInstance(String apikey,OkHttpClient basclient) throws IOException
		{
			if(BASHTTPClient.client == null){
				BASHTTPClient.client = new BASHTTPClient(apikey, basclient);
			}
			else{
				BASHTTPClient.client.setAPikey(apikey);
			}
			return BASHTTPClient.client;
		}

		public void setAPikey(String apikey)
		{
			this.apikey = apikey;
		}


		public void clearFilePaths(){
			RetrieveCSVQuery = "";

			csvData = null;

		}

		public void updateFilePaths(String[] paths){
			this.RetrieveCSVQuery = paths[0];

		}

		private void getFilePaths() throws IOException {

			OkHttpClient client = Basclient;
			HttpUrl url = apiBase.newBuilder()
					.addPathSegment("grabfilestrings")
					.build();

			Request request = new Request.Builder()
					.header("User-Agent", "RuneLite")
					.url(url)
					.header("Content-Type", "application/json")
					.header("x-api-key", this.apikey)
					.build();

			Response test;
			client.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					updateFilePaths(response.body().string().split("\"")[3].split(","));
				}
			});

		}

		@Override
		public boolean markCustomer(int option, String name,String rankName) throws IOException
		{
			OkHttpClient client = Basclient;
			HttpUrl url = apiBase.newBuilder()
					.addPathSegment("queue")
					.build();

			/*
				3 == online
				0 == cooldown
				2 == done
				1 == in progress
			}*/


			Request request = new Request.Builder()
					.header("User-Agent", "RuneLite")
					.url(url)
					.header("Content-Type", "application/json")
					.header("x-api-key", this.apikey)
					.header("username",name)
					.header("action", String.valueOf(option))
					.header("rankname", rankName)
					.build();

			client.newCall(request).enqueue(new Callback()
			{
				@Override
				public void onFailure(Call call, IOException e)
				{

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException { String eat =(response.body().string()); }
			});
			return true;

		}

		public boolean updateQueuebackend(StringBuilder urlList, String name) throws IOException {
			OkHttpClient client = Basclient;
			HttpUrl url = apiBase.newBuilder()
					.addPathSegment("queuespecific")
					.build();

			Request request = new Request.Builder()
					.header("User-Agent", "RuneLite")
					.url(url)
					.header("Content-Type", "application/json")
					.header("x-api-key", this.apikey)
					.header("returncsv", "0")//treat 0/1 as true/false respectively
					.header("rankname", name)
					.header("csv", urlList.toString().replace("\u00A0", " "))
					.build();

			client.newCall(request).enqueue(new Callback()
			{
				@Override
				public void onFailure(Call call, IOException e)
				{

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException { String eat = (response.body().string()+"\n"); }
			});
			return true;
		}

		@Override
		public List<String[]> readCSV(List<String[]> csv) throws IOException {
			OkHttpClient client = Basclient;
			HttpUrl url = apiBase.newBuilder()
					.addPathSegment("queuespecific")
					.build();

			Request request = new Request.Builder()
					.header("User-Agent", "RuneLite")
					.url(url)
					.header("Content-Type", "application/json")
					.header("x-api-key", this.apikey)
					.header("returncsv", "1")//treat 0/1 as true/false respectively
					.header("rankname", "get")
					.build();

			client.newCall(request).enqueue(new Callback()
			{
				@Override
				public void onFailure(Call call, IOException e)
				{

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					String data = response.body().string().split("body\": \"")[1].replace("}","");
					 csvData = new ArrayList<>();
					String[] lines = data.split("\\\\r\\\\n");

					for (String line : lines) {
						String[] values = line.split(",");
						csvData.add(values);
					}
					response.close();

				}
			});
			return csvData;//by way of how the enqueue/callback works this is always 1 refresh behind.... not the end of the world but annoying
		}


		@Override
		public boolean addCustomer(String itemName, String priority, String custName, String addedBy) throws IOException
		{
			OkHttpClient client = Basclient;

			// Build the URL for your Google Form submission.
			HttpUrl url = new HttpUrl.Builder()
					.scheme("https")
					.host("docs.google.com")
					.addPathSegment("forms")
					.addPathSegment("d")
					.addPathSegment("e")
					// Replace with your actual Google Form ID from the URL:
					// https://docs.google.com/forms/d/e/1FAIpQLSc06_IrTbleP0uZBiOt1yMcI5kvOrvkzgaVLLmEDRLqJSSoVg/viewform
					.addPathSegment("1FAIpQLSc06_IrTbleP0uZBiOt1yMcI5kvOrvkzgaVLLmEDRLqJSSoVg")
					.addPathSegment("formResponse")
					.build();

			// Build the form-encoded request body with the proper entry IDs.
			okhttp3.RequestBody formBody = new FormBody.Builder()
					.add("entry.1481518570", priority)
					.add("entry.1794472797", custName)
					.add("entry.1391010025", itemName)
					.add("entry.1284888696", addedBy)
					.add("entry.1260617128", RetrieveCSVQuery)
					.build();

			// Create a POST request to the Google Form.
			Request request = new Request.Builder()
					.url(url)
					.post(formBody)
					.header("User-Agent", "RuneLite")
					.build();

			client.newCall(request).enqueue(new Callback()
			{
				@Override
				public void onFailure(Call call, IOException e)
				{

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException { String eat =(response.body().string()); }
			});
			return true;
		}

		@Override
		public boolean sendChatMsgDiscord(ChatMessage chatmessage) throws IOException
		{
			String unhashedMsg = chatmessage.getName() + chatmessage.getMessage() + (((int)(chatmessage.getTimestamp()/10)*10));

			int hasedMsg = unhashedMsg.hashCode();
			OkHttpClient client = Basclient;
			HttpUrl url = apiBase.newBuilder()
					.addPathSegment("disc")
					.build();

			Request request = new Request.Builder()
					.header("User-Agent", "RuneLite")
					.url(url)
					.header("Content-Type", "application/json")
					.header("x-api-key", this.apikey)
					.header("username",chatmessage.getName().replace(' ', ' '))
					.header("msg",chatmessage.getMessage())
					.header("hash",String.valueOf(hasedMsg))
					.build();

			client.newCall(request).enqueue(new Callback()
			{
				@Override
				public void onFailure(Call call, IOException e)
				{

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException { response.close(); }
			});
			return true;
		}

		@Override
		public boolean sendRoundTimeServer(String main, String collector, String healer, String leech, String defender, int time, int premiumType, String item
				,int attpts, int defpts, int healpts, int collpts, int eggsCollected, int hpHealed, int wrongAtts, String leechrole) {
			ZonedDateTime currentTimeUTC = ZonedDateTime.now(ZoneOffset.UTC);
			int seconds = currentTimeUTC.getSecond();
			int roundedSeconds = (seconds / 30) * 30; // Round to the nearest 10 seconds for use in the hash/prevent multiple same as discord msgs
			ZonedDateTime roundedTime = currentTimeUTC.withSecond(roundedSeconds).withNano(0);
			String roundedTimestampUTC = roundedTime.format(DateTimeFormatter.ISO_DATE_TIME);

			String unhashedMsg = main + collector + healer + leech + defender + roundedTimestampUTC;

			int hasedMsg = unhashedMsg.hashCode();

			OkHttpClient client = Basclient;
			HttpUrl url = apiBase.newBuilder()
					.addPathSegment("recordRound")
					.build();


			Request request = new Request.Builder()
					.header("User-Agent", "RuneLite")
					.url(url)
					.header("Content-Type", "application/json")
					.header("x-api-key", this.apikey)
					.header("main",main)
					.header("collector",collector)
					.header("healer",healer)
					.header("leech",leech)
					.header("defender",defender)
					.header("time", String.valueOf(time))
					.header("premiumtype",String.valueOf(premiumType))
					.header("item", item)
					.header("hash",String.valueOf(hasedMsg))
					.header("attpts", String.valueOf(attpts))
					.header("defpts", String.valueOf(defpts))
					.header("healpts", String.valueOf(healpts))
					.header("collpts", String.valueOf(collpts))
					.header("eggscollected", String.valueOf(eggsCollected))
					.header("hphealed", String.valueOf(hpHealed))
					.header("wrongatts", String.valueOf(wrongAtts))

					.header("leechrole", String.valueOf(leechrole))
					.build();

			client.newCall(request).enqueue(new Callback()
			{
				@Override
				public void onFailure(Call call, IOException e)
				{

				}

				@Override
				public void onResponse(Call call, Response response) throws IOException { response.close(); }
			});
			return true;
		}





	}

	/*
	 * Copyright (c) 2019, TheStonedTurtle <https://github.com/TheStonedTurtle>
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