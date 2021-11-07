package Denys_bot.BOT_Telgramm_01.service;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;

@Component
public class MateAcademyBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Denyska352_test_bot";
    }

    @Override
    public String getBotToken() {
        return "2057909343:AAHtLhsqVuUVLwF1uTaxePnVZdpcaf3bzMY";
    }

    @Override
    public void onUpdateReceived(Update update) {


        Message message = update.getMessage();

        if(message.getLeftChatMember() != null)
        {
            String chat_id = update.getMessage().getChatId().toString();
            String filePath = "D:\\java\\Spring\\BOT_Telgramm_0.1\\died.gif";
            File file = new File(filePath);

            if (!file.exists())
                System.out.println("no such file");
            try {
                InputFile inputStream = new InputFile(file);
                SendDocument sendDocumentRequest = new SendDocument();
                sendDocumentRequest.setChatId(message.getChatId().toString());
                sendDocumentRequest.setDocument(inputStream);
                sendDocumentRequest.setCaption("-1 ((((((((((");
                try
                {
                    execute(sendDocumentRequest);
                }
                catch (TelegramApiException e)
                {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //SendMessage sendmessage = new SendMessage();

           // sendmessage.setChatId(chat_id);
            //sendmessage.setText("-1 ((((((((((");

            System.out.println(message.getLeftChatMember());
            System.out.println("Delete message geted");
        }
    }
}
