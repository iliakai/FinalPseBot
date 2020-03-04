import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Contact;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class PseBot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new PseBot());
        }
        catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        }
        catch(TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void rplysendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        }
        catch(TelegramApiException e){
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String[] namearray1 = new String[] {"Илья","Илья","Миша","Вася","Олег","Саня","Сережа","Илюха","Мишаня","Васек","Олежа","Санек","Серега","Серега"};
        String[] namearray2 = new String[] {"Илью","Илью","Мишу","Васю","Олега","Саню","Сережу","Илюху","Мишаню","Васка","Олежу","Санька","Серегу","Серегу"};
        String[] phrasearray1 = new String[] {"ебешь ли псееее","Шла босикооом...","нее жалея ног","И все вместе! Ебешь ли псеее вдоль ночных дорог","Ты тоже любишь Максим?"};

        if(message!=null && message.hasText()){
            int i = (int) Math.round(Math.random()*20);
            int n = (int) Math.round(Math.random()*(namearray1.length-1));
            int p = (int) Math.round(Math.random()*(phrasearray1.length-1));
            if (((i==3)||(i==13))&&(message.getText().length()>2 && message.getText().length()<15)&&(message.getText().lastIndexOf(" ")==-1)) {
                sendMsg(message, "Ебешь ли "+message.getText()+" вдоль ночных дорог"); return;
            }
            if (i==5) {
                rplysendMsg(message, "Ты пиздец"); return;
            }
            if (i==7) {
                rplysendMsg(message, "Ты в своем стиле, чувак"); return;
            }
            if (i==8) {
                sendMsg(message, "Ставлю, что завтра пидором будет "+namearray1[n-1]); return;
            }
            if (i==12) {
                sendMsg(message, "50 на "+namearray2[n-1]); return;
            }
            if (i==14) {
                sendMsg(message, "Где "+namearray1[n-1]+"? Ау?"); return;
            }
            if (message.getText().toLowerCase().lastIndexOf("псе")!=-1) {
                sendMsg(message,phrasearray1[p]); return;
            }
            if (message.getText().toLowerCase().lastIndexOf("псина")!=-1) {
                sendMsg(message,phrasearray1[p]); return;
            }
            if (message.getText().toLowerCase().lastIndexOf("ебеш ли псе")!=-1) {
                sendMsg(message,phrasearray1[p]); return;
            }
        }
    }

    public String getBotUsername() {
        return "PseBot";
    }

    public String getBotToken() {
        return "1021123742:AAGqqbxVRnde1xg_yjicPR_DZ8Gw0rS7Yp4";
    }
}