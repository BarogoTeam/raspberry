import telegram
import sys

chat_id = sys.argv[1]
msg = sys.argv[2]

print(chat_id)
print(msg)

my_token = '550340437:AAFHDGQF0mTDNM1FqZp1K-KEJHLCXrEPG_U'

bot = telegram.Bot(token= my_token)
bot.sendMessage(chat_id = chat_id, text=msg)