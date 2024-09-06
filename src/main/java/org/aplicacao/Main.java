package org.aplicacao;
import java.util.List;
import org.aplicacao.models.ComprasitensPost;
import org.aplicacao.models.Comprasitens;
import org.aplicacao.services.ApiServices;

import javax.management.openmbean.CompositeType;
import java.util.Random;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        // Gerar um número inteiro aleatório entre 0 e 99 (exclusivo)
        int randomNumber = random.nextInt(2000);
        ApiServices servicoApi = new ApiServices();

        //Get paginado com limite
        List<Comprasitens> paginada = servicoApi.getComprasItens(1,10,"http://localhost:3060/comprasitens");

        //Get por id
        int id = paginada.get(1).getId();
        Comprasitens listadaPorId = servicoApi.getIdComprasItens("http://localhost:3060/comprasitens",id);

        //Setando valor diferente e ataualizando
        listadaPorId.setValor(randomNumber);
        Comprasitens atualizada = servicoApi.putComprasItens("http://localhost:3060/comprasitens",listadaPorId, id);

        //Criando novo elemento
        //Pegando ids ja existentes
        int comprasId = paginada.get(3).getErp_compras_id();
        int produtosId = paginada.get(5).getErp_produtos_id();

        //Criando a data no padrão iso
        ZonedDateTime now = ZonedDateTime.now();
        String iso8601Date = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        //Criando o objeto
        ComprasitensPost novoElemento = new ComprasitensPost(300,produtosId,comprasId, randomNumber,iso8601Date);

        Comprasitens apiCriado = servicoApi.postComprasItens("http://localhost:3060/comprasitens", novoElemento);


        //Excluindo um elemento

        Comprasitens elementoExcluido = servicoApi.deleteComprasItens("http://localhost:3060/comprasitens", apiCriado.getId());

        System.out.println(paginada.get(9).toString());
        System.out.println(listadaPorId.toString());
        System.out.println(atualizada.toString());
        System.out.println(apiCriado.toString());
        System.out.println(elementoExcluido.toString());

    }
}