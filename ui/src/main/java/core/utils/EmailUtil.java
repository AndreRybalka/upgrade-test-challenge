package core.utils;

import com.sun.mail.imap.IMAPStore;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static core.SLF4JLogger.error;
import static core.SLF4JLogger.info;
import static core.SLF4JLogger.log;
import static core.helpers.WebElementsVerificationsHelper.verifyConditionIsTrue;
import static core.utils.WaitingUtils.sleep;

public class EmailUtil {

    private final static String FOLDER = "inbox";
    private final static String PROTOCOL = "imaps";
    private final static String MESSAGE_BODY_TEXT = "Click below to reset your password. Didn't request this email?";
    private final static String MESSAGE_SUBJECT = "Reset Your Password";
    private final static String MESSAGE_SENDER = "ALDO";

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        } else if (message.isMimeType("text/html")) {
            String body = (String) message.getContent();
            result = org.jsoup.Jsoup.parse(body).text();
        }
        return result;
    }

    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart) throws MessagingException, IOException {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append("\n").append(bodyPart.getContent());
                break;
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result.append("\n").append(org.jsoup.Jsoup.parse(html).text());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }

    private void verifyMessageInfo(List<Message> array) throws MessagingException, IOException {
        info("Unread Messages Count : " + array.size());
        for (Message message : array) {
            String subject = message.getSubject();
            if (subject.equals(MESSAGE_SUBJECT) &
                    message.getFrom()[0].toString().contains(MESSAGE_SENDER)) {
                info("*****************************************************************************");
                info("MESSAGE " + (array.indexOf(message) + 1) + ":");
                info("FROM: " + message.getFrom()[0]);
                info("DATE: " + message.getReceivedDate());
                info("MESSAGE_TYPE: " + message.getContentType());
                info("TEXT: " + getTextFromMessage(message));
                verifyConditionIsTrue(getTextFromMessage(message).contains(MESSAGE_BODY_TEXT), "Body text is a respective one");
            }
        }
    }

    private void closeConnection(Store store, Folder folder) throws MessagingException {
        if (folder != null && folder.isOpen()) {
            folder.close(true);
        }
        if (store != null) {
            store.close();
        }
    }

    public void check(String mail, String password, int minutes) {
        List<Message> array = null;
        IMAPStore store = null;
        Folder inbox = null;
        try {
            Properties props = System.getProperties();
            props.put("mail.store.protocol", PROTOCOL);
            props.put("mail.imaps.host", "imap.gmail.com");
            props.put("mail.imaps.port", "993");
            props.put("mail.imaps.timeout", "10000");
            Session session = Session.getInstance(props);
            store = (IMAPStore) session.getStore(PROTOCOL);

            for (int i = 1; i <= minutes; i++) {
                store.connect("imap.googlemail.com", mail, password);
                inbox = store.getFolder(FOLDER);

                if (!inbox.isOpen())
                    inbox.open(Folder.READ_WRITE);
                array = Arrays.asList(inbox.search(new FlagTerm(new Flags(
                        Flags.Flag.SEEN), false)));
                if (array.size() == 0 & i < minutes ) {
                    log("Waiting...");
                    sleep(60000);
                    log(i + " minute has passed.");
                    closeConnection(store, inbox);
                } else if (array.size() == 0 & i >= minutes) {
                    closeConnection(store, inbox);
                    throw new RuntimeException("Expected time was passed but no unread messages has appeared");
                } else {
                    log("Waiting is successfully over");
                    break;
                }
            }

            assert array != null;
            verifyMessageInfo(array);
            for (Message message: array){
                message.setFlag(Flags.Flag.DELETED, true);
            }

            if (array.size() > 1) {
                throw new RuntimeException("There is more than 1 message. Current unread message amount is : " + array.size());
            }
        } catch (IOException | MessagingException e) {
            error("There is an error due email message handling");
            e.printStackTrace();
        } finally {
            try {
                closeConnection(store, inbox);
            } catch (MessagingException e) {
                error("There is an error due folder/store closing");
                e.printStackTrace();
            }
        }
    }
}
