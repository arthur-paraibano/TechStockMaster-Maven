package com.techstockmaster.controller;

import com.techstockmaster.model.dao.UserDAO;
import com.techstockmaster.model.entities.Feedback;
import com.techstockmaster.model.entities.User;
import com.techstockmaster.service.Gmail;
import com.techstockmaster.util.Message;

import javax.swing.*;
import java.sql.SQLException;


public class ShippingGmail {
    private UserDAO userDAO;
    private Gmail envio;

    public ShippingGmail() {
        this.userDAO = new UserDAO();
        this.envio = new Gmail();

    }

    public boolean envioGmail(String gmail, String token) {
        boolean chek = false;
        try {
            User user = new User();
            user.setGmailHotmail(gmail);
            user.setSenha(token);

            boolean usuario = userDAO.tokenUpdate(user, token);

            if (usuario) {
                envio.shippingGmail(gmail, token);
                chek = true;
            } else {
                chek = false;
            }

        } catch (SQLException ex) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Message.fatal(null,
                            "Erro ao atualizar a senha no banco de dados (tokenUpdate). \nEntre em contato com o administrador do sistema."
                                    + ex);
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return chek;
    }

    public boolean feedbackGmail(Feedback obj) {
        boolean chek = false;
        try {
            String user = obj.getUser().getNomeLogin();
            String coment = obj.getComment();
            envio.feedbackgGmail(user, coment);
            chek = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return chek;
    }
}
