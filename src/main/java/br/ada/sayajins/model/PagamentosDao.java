package br.ada.sayajins.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class PagamentosDao {
    private static PagamentosDao instance; 
    private List<Pagamentos> listaPagamentos;

    private PagamentosDao() throws IOException{
        listaPagamentos = new ArrayList<Pagamentos>();
        File file =  new File("src/main/resources/pagamentos.csv");
        try (Stream<String> s = Files.lines(file.toPath())) {
            s.forEach(linha->{
                String[] dados = linha.split(";");
                if(!dados[0].equals("Nome")){
                    String nome = dados[0];
                    int ano =Integer.parseInt(dados[2].substring(0, 4));
                    int mes = Integer.parseInt(dados[2].substring(4, 6));
                    int dia = Integer.parseInt(dados[2].substring(6, 8));
                    double valor = Double.parseDouble(dados[3]);
                    LocalDate data = LocalDate.of(ano, mes, dia);
                    TipoPagamentoEnum tipo = TipoPagamentoEnum.tipoEnum(dados[1]);
                    Pagamentos pag = new Pagamentos(nome, data, valor, tipo);  
                    listaPagamentos.add(pag);   
                }
    });
        }
    
    }

    public List<Pagamentos> getAll(){
        return Collections.unmodifiableList(listaPagamentos);
    }

    public static PagamentosDao getInstance() throws IOException{
        if(instance == null){
           instance = new PagamentosDao();
        }
        return instance;
    }

    @Override
    public String toString() {
        return listaPagamentos.toString();
    }

}
