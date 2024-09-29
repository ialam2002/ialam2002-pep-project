package Service;

import DAO.SocialMediaDAO;
import Model.Account;
import Model.Message;

import java.util.List;

public class SocialMediaService {
    private final SocialMediaDAO dao;

    public SocialMediaService(SocialMediaDAO dao) {
        this.dao = dao;
    }

    public Account registerAccount(Account account) {
        if (account.getUsername() == null || account.getUsername().isEmpty() || account.getPassword().length() < 4) {
            return null; // Invalid registration
        }
        return dao.createAccount(account);
    }

    public Account loginAccount(String username, String password) {
        return dao.getAccountByUsernameAndPassword(username, password);
    }

    public Message createMessage(Message message) {
        if (message.getMessage_text() == null || message.getMessage_text().isEmpty() || message.getMessage_text().length() > 255 || dao.getAccountById(message.getPosted_by()) == null) {
            return null;
        }
        return dao.createMessage(message);
    }
    

    public List<Message> getAllMessages() {
        return dao.getAllMessages();
    }

    public Message getMessageById(int messageId) {
        return dao.getMessageById(messageId);
    }

    public boolean deleteMessage(int messageId) {
        return dao.deleteMessage(messageId);
    }

    public Message updateMessage(int messageId, String messageText) {
        if (messageText == null || messageText.isEmpty() || messageText.length() > 255) {
            return null;
        }
        return dao.updateMessage(messageId, messageText);
    }

    public List<Message> getMessagesByAccountId(int accountId) {
        return dao.getMessagesByAccountId(accountId);
    }
}
