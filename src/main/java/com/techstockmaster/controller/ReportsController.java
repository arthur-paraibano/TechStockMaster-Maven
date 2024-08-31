package com.techstockmaster.controller;

import com.techstockmaster.util.base.DatabaseSist;
import com.techstockmaster.view.login.Loading;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

public class ReportsController {
    private final String nome = "Gerando relatório";
    private Loading loading;

    public void usuarioCadastrado() {
        gerarRelatorio("/com/techstockmaster/reports/UsuárioCadastrado.jrxml");
    }

    public void equipamentoCadastrado() {
        gerarRelatorio("/com/techstockmaster/reports/EquipamentosCadastrados.jrxml");
    }

    public void equipamentoTag() {
        gerarRelatorio("/com/techstockmaster/reports/EquipamentosCTag.jrxml");
    }

    public void setorCadastrado() {
        gerarRelatorio("/com/techstockmaster/reports/Setor(es)Cadastrado(s).jrxml");
    }

    public void tagCadastradas() {
        gerarRelatorio("/com/techstockmaster/reports/Tag(s)Cadastrado(s).jrxml");
    }

    public void estoque() {
        gerarRelatorio("/com/techstockmaster/reports/EstoqueDeMaterial.jrxml");
    }

    public void chamadosTI() {
        gerarRelatorio("/com/techstockmaster/reports/ChamadosTI.jrxml");
    }

    public void concertos() {
        gerarRelatorio("/com/techstockmaster/reports/ConcertosEquipamentos.jrxml");
    }

    public void solicitacaoCompras() {
        gerarRelatorio("/com/techstockmaster/reports/SolicitaçãoDeComprasTI.jrxml");
    }

    public void controleDeSaida() {
        gerarRelatorio("/com/techstockmaster/reports/ControleDeSaidas.jrxml");
    }

    private void gerarRelatorio(String reportPath) {
        loading = new Loading(nome);
        Thread thread = new Thread(() -> {
            try (Connection connection = DatabaseSist.getConnection()) {
                InputStream jasperFile = getClass().getResourceAsStream(reportPath);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperFile);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), connection);

                JasperExportManager.exportReportToPdf(jasperPrint);
                exibirRelatorio(jasperPrint);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                loading.dispose();
            }
        });
        thread.start();
        loading.setVisible(true);
    }

    private void exibirRelatorio(JasperPrint jasperPrint) {
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
        viewer.setAlwaysOnTop(true);
        viewer.setVisible(true);
    }
}