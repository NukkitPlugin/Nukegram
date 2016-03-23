package kr.mohi.nukegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.response.SendResponse;

import cn.nukkit.plugin.PluginBase;

abstract public class Main extends PluginBase {
	DataBase database;
	private TelegramBot bot;

	@Override
	public void onEnable() {
		this.database = new DataBase(this);
		database.loadDB();
		TelegramBot bot = TelegramBotAdapter.build(database.getToken());
	}
	public SendResponse sendMessage(Object chatId, String text) {
		return bot.sendMessage(chatId, text);
	}
	public SendResponse sendMessage(Object chatId, String text, ParseMode parse_mode, Boolean disableWebPagePreview, Integer replyToMessageId, Keyboard replyMarkup) {
		return bot.sendMessage(chatId, text, parse_mode, disableWebPagePreview, replyToMessageId, replyMarkup);
	}
	
}
