from telegram.ext import Updater, CommandHandler, MessageHandler, Filters
import pymongo
import logging
import os
import random

logging.basicConfig(format='%(asctime)s - %(name)s - %(levelname)s - %(message)s', level=logging.INFO)

logger = logging.getLogger(__name__)

def start(bot, update):
    chat_id = update.message.chat_id
    user = update.message.from_user
    msg = "http://183.101.136.11:3000/signin?chatId="+str(chat_id)
    bot.sendMessage(update.message.chat_id, text=msg)


def query(msg) :
    return "/help maybe help you"

def help(bot, update):
    msg = '/signup : register your password'
    msg += '\n/start : receive signin URL'
    msg += '\n/pw : change your password\n\t\tex)/pw abc123'
    bot.sendMessage(update.message.chat_id, text=msg)

def signup(bot, update):
    chat_id = str(update.message.chat_id)
    user = update.message.from_user
    password = str(random.randrange(1000,10000))
    
    try:
        conn = pymongo.MongoClient("mongodb://barogo:barogo123!@ds263380.mlab.com:63380/zupzup")
        db = conn.get_database('zupzup')
        collection = db.get_collection("users")
        collection.insert({"email":chat_id,"password":password})
        
        msg = 'Your password is ['+password+']'
        bot.sendMessage(update.message.chat_id, text=msg)
        msg = 'http://183.101.136.11:3000/signin?chatId='+str(chat_id)
        bot.sendMessage(update.message.chat_id, text=msg)
    except:
        bot.sendMessage(update.message.chat_id, text="Already signed up")

def pw(bot, update):
    chat_id = str(update.message.chat_id)
    user = update.message.from_user
    password = update.message.text[4:]
    
    try:
        conn = pymongo.MongoClient("mongodb://barogo:barogo123!@ds263380.mlab.com:63380/zupzup")
        db = conn.get_database('zupzup')
        collection = db.get_collection("users")
        collection.update({"email":chat_id},{"email":chat_id,"password":password})
        
        msg = ("Your password changed to ["+password+"]")
        bot.sendMessage(update.message.chat_id, text=msg)
    except:
        bot.sendMessage(update.message.chat_id, text="Undefined Error")

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
    dp.add_handler(CommandHandler("signup", signup))
    dp.add_handler(CommandHandler("pw",pw))
    
    dp.add_handler(MessageHandler([Filters.text],response))
    updater.start_polling()
    updater.idle()


if __name__ == '__main__':
    main()
