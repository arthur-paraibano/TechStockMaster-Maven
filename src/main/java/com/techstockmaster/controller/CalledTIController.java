package com.techstockmaster.controller;

import com.techstockmaster.model.dao.CalledTIDAO;
import com.techstockmaster.model.entities.CalledTI;
import com.techstockmaster.model.entities.Sector;
import com.techstockmaster.util.Message;

import java.util.ArrayList;
import java.util.List;

public class CalledTIController {

    private final CalledTIDAO dao;
    private final SectorController sectorController;

    public CalledTIController() {
        this.dao = new CalledTIDAO();
        this.sectorController = new SectorController();
    }

    public boolean save(CalledTI obj) {
        try {
            this.dao.add(obj);
            //Message.sucess(null, "Chamado de TI cadastrado!!!");
            return true;
        } catch (Exception e) {
          //  Message.errorX(null, "Erro ao listar: 'salvar(chamdo ti)' " + e.getMessage());
            return false;
        }
    }

    public List<Sector> findAll() {
        return sectorController.findAll();
    }

    public List<CalledTI> findAllTable() {
        try {
            return dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
          //  Message.errorX(null, "Erro ao listar: 'findAllTable' " + e.getMessage() + e.getClass().getName());
            return new ArrayList<>();
        }
    }
}