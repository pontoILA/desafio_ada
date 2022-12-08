package br.ada.sayajins;

import java.io.IOException;
import java.time.LocalDate;

import br.ada.sayajins.DAO.PagamentosDao;

public class Main {
    public static void main(String[] args) throws IOException {
        PagamentosDao pagDao = PagamentosDao.getInstance();
        // pagDao.getAll().forEach(pag ->{
        //     pag.gerarAcrescimo();
        //     System.out.println(pag);
        // });


        
        pagDao.getAll().forEach(System.out::println);
        System.out.println("---------------------------------------------------------ATRASADOS---------------------------------------------------");
        pagDao.getAll().stream().filter(pag->pag.getDtVencto().isBefore(LocalDate.now())).map(pag-> {pag.gerarAcrescimo(); return pag;}).forEach(System.out::println);
        System.out.println("---------------------------------------------------------ADIANTADOS---------------------------------------------------");
        pagDao.getAll().stream().filter(pag->pag.getDtVencto().isAfter(LocalDate.now())).map(pag-> {pag.gerarDecrescimo(); return pag;}).forEach(System.out::println);
        
        pagDao.salvarEmArquivo();
    }

}
