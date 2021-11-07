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
            System.out.println(message.getLeftChatMember());
            System.out.println("Delete message geted");
        }
        String filePath = "D:\\java\\File\\qwer.pdf";
        String filePath2 = "D:\\java\\File\\qwer1.pdf";
        File file = new File(filePath);
        File file2 = new File(filePath2);

        if (!file.exists())
            System.out.println("no such file");
        try {
            // inout stream from file
            InputStream inputStream = new FileInputStream(file);

            // we create a reader for a certain document
            PdfReader reader = new PdfReader(inputStream);


            // we create a stamper that will copy the document to a new file
            PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(file2));

            // adding content to each page
            int i = 0;
            PdfContentByte under;
            under = stamp.getOverContent(1);
            under.beginText();
            under.setFontAndSize(BaseFont.createFont(),15);
            under.setTextMatrix(20,600);
            under.showText(message.getText());

            under.endText();
            stamp.close();

        }
        catch(Exception de) {
            de.printStackTrace();
        }
        InputFile inputStream2 = new InputFile(file2);
        System.out.println(message.getText());
        SendDocument sendDocumentRequest = new SendDocument();
        sendDocumentRequest.setChatId(message.getChatId().toString());
        sendDocumentRequest.setDocument(inputStream2);

        //sendDocumentRequest.setThumb(inputStream2);


        //sendDocumentRequest.setCaption(caption);
        //SendMessage sendMessage = new SendMessage();
        //sendMessage.setText("Hello " + message.getText());
        //sendMessage.setChatId(message.getChatId().toString());
        try {
            execute(sendDocumentRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
