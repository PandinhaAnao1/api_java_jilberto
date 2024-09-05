package org.aplicacao;
import java.util.List;

import org.aplicacao.models.Comprasitens;
import org.aplicacao.services.ApiServices;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {

        ApiServices buscarItens = new ApiServices();

        List<Comprasitens> paginada = buscarItens.getComprasItens(10,19,"http://localhost:3060/comprasitens");

        int id = paginada.get(1).getId();
        Comprasitens listadaPorId = buscarItens.getIdComprasItens("http://localhost:3060/comprasitens",id);

        List<Comprasitens> atualizada = buscarItens.putComprasItens("http://localhost:3060/comprasitens",listadaPorId, id);

        System.out.println(paginada.get(9).toString());
        System.out.println(listadaPorId.toString());
        System.out.println(atualizada.toString());

    }
}