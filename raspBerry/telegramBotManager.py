from telegram.ext import Updater, CommandHandler, MessageHandler, Filters
import logging
import os
import random

logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.INFO)

logger = logging.getLogger(__name__)

def start(bot, update):
    chat_id = update.message.chat_id
    user = update.message.from_user
    msg = "http://183.101.136.11:3000?chatId="+str(chat_id)
    bot.sendMessage(update.message.chat_id, text=msg)

def query(msg) :
  return "/help maybe help you ;)"

def help(bot, update):
    bot.sendMessage(update.message.chat_id, text='/start : receive signin URL')

def error(bot, update, error):
    logger.warn('Update "%s" caused error "%s"' % (update, error))

def response(bot, update):
    chat_id = update.message.chat_id
    user = update.message.from_user
    user_name = "%s%s" %(user.last_name, user.first_name)
    r_msg = query(update.message.text)
    bot.sendMessage(chat_id, text=r_msg)

def main():
    token = '550340437:AAFHDGQF0mTDNM1FqZp1K-KEJHLCXrEPG_U'

    updater = Updater(token)
    dp = updater.dispatcher

    dp.add_handler(CommandHandler("help",help))
    dp.add_handler(CommandHandler("start", start))

    dp.add_handler(MessageHandler([Filters.text],response))
    updater.start_polling()
    updater.idle()


if __name__ == '__main__':
    main()