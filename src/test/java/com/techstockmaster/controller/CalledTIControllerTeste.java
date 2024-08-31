package com.techstockmaster.controller;

import com.techstockmaster.model.dao.CalledTIDAO;
import com.techstockmaster.model.entities.CalledTI;
import com.techstockmaster.model.entities.Sector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CalledTIControllerTeste {

    @Mock
    private CalledTIDAO dao;

    @Mock
    private SectorController sectorController;

    @InjectMocks
    private CalledTIController controller;

    @Test
    void testSaveSuccess() throws Exception {
        CalledTI calledTI = new CalledTI();

        // Simula a adição sem erro
        doNothing().when(dao).add(calledTI);

        boolean result = controller.save(calledTI);

        assertTrue(result, "Deveria retornar verdadeiro ao salvar com sucesso.");
        verify(dao, times(1)).add(calledTI);
    }

    @Test
    void testSaveFailure() throws Exception {
        CalledTI calledTI = new CalledTI();

        // Simula uma exceção ao adicionar
        doThrow(new RuntimeException("Erro simulado")).when(dao).add(calledTI);

        boolean result = controller.save(calledTI);

        assertFalse(result, "Deveria retornar falso ao ocorrer uma exceção.");
        verify(dao, times(1)).add(calledTI);
    }

    @Test
    void testFindAll() {
        List<Sector> sectors = new ArrayList<>();
        when(sectorController.findAll()).thenReturn(sectors);

        List<Sector> result = controller.findAll();

        assertEquals(sectors, result, "O resultado deveria ser o mesmo da lista mockada.");
        verify(sectorController, times(1)).findAll();
    }

    @Test
    void testFindAllTableSuccess() throws Exception {
        List<CalledTI> calledTIList = new ArrayList<>();
        when(dao.findAll()).thenReturn(calledTIList);

        List<CalledTI> result = controller.findAllTable();

        assertEquals(calledTIList, result, "O resultado deveria ser o mesmo da lista mockada.");
        verify(dao, times(1)).findAll();
    }

    @Test
    void testFindAllTableFailure() throws Exception {
        when(dao.findAll()).thenThrow(new RuntimeException("Erro simulado"));

        List<CalledTI> result = controller.findAllTable();

        assertTrue(result.isEmpty(), "A lista deveria estar vazia ao ocorrer uma exceção.");
        verify(dao, times(1)).findAll();
    }
}