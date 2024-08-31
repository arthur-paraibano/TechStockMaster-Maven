package com.techstockmaster.controller;

import com.techstockmaster.model.dao.FeedbackDAO;
import com.techstockmaster.model.entities.Feedback;
import com.techstockmaster.util.Message;
import com.techstockmaster.view.login.Loading;


import javax.swing.*;

public class FeedbackController {
    private FeedbackDAO dao;

    public FeedbackController() {
        this.dao = new FeedbackDAO();
    }

    public void addFeedback(Feedback obj) {
        Loading loading = new Loading();
        Thread emailThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ShippingGmail envio = new ShippingGmail();
                    boolean emailSent = envio.feedbackGmail(obj);
                    loading.setVisible(false);

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (!emailSent) {
                                Message.erroTrist(null, "Falha no envio de email");
                            } else {
                                try {
                                    dao.add(obj);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } catch (Exception ex) {
                    Message.erroTrist(null, "Erro ao enviar o Email! 'addFeedback': " + ex.getMessage());
                }
            }
        });
        emailThread.start();
        loading.setVisible(true);
    }
}