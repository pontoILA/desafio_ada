package br.ada.sayajins;

import java.io.IOException;

import br.ada.sayajins.DAO.PagamentosDao;

public class Main {
    public static void main(String[] args) throws IOException {
        PagamentosDao pagDao = PagamentosDao.getInstance();
        pagDao.getAll().forEach(System.out::println);
    }

}
