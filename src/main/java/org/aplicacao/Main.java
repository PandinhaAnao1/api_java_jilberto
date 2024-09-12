package org.aplicacao;
import java.util.List;
import org.aplicacao.models.ComprasitensPost;
import org.aplicacao.models.Comprasitens;
import org.aplicacao.services.ApiServices;

import javax.management.openmbean.CompositeType;
import java.util.Random;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        String regex = "^(C|E|A|L|LI)$";

        // Criando o pattern e matcher
        Pattern pattern = Pattern.compile(regex);

        String opiton;
        boolean controle = true;
        while (controle){
            System.out.println("Sistema finaceiro o que deseja fazer?");
            System.out.println("C - Criar");
            System.out.println("E - Excluir");
            System.out.println("A - Atualizar");
            System.out.println("L - listar");
            System.out.println("LI - listar por id");

            System.out.println("Escolha uma opção dentro dessas caso seja selecionada outra voce encerar o progama: ");
            opiton = scanner.next();
            Matcher matcher = pattern.matcher(opiton);

            if(!matcher.matches()){
                controle = false;
                System.out.println("Opção invalida ele deve constar entra as acima (A, L, LI, E, C)");
                break;
            }
            ApiServices servicoApi = new ApiServices();
            switch (opiton) {
                case "C":
                    System.out.println("Informe o valor da compra: ");
                    float valor = scanner.nextFloat();
                    System.out.println("Informe o id do produto: ");
                    int erp_produtos_id = scanner.nextInt();
                    System.out.println("Informe a quantidade: ");
                    int qtd = scanner.nextInt();
                    System.out.println("Iforme o id da compra: ");
                    int erp_compras_id = scanner.nextInt();
                    System.out.println("Iforme a data no formato YYYY-MM-DDThh, na iso 8601: ");
                    String data_lancamento = scanner.next();
                    ComprasitensPost novoitem = new ComprasitensPost(valor,
                            erp_produtos_id,
                            qtd,
                            erp_compras_id,
                            data_lancamento);
                    Comprasitens novo =  servicoApi.postComprasItens("http://localhost:3060/comprasitens",novoitem);
                    System.out.println("Novo item criado!");
                    System.out.println(novo.toString());
                    break;
                case "E":
                    System.out.println("Opção Excluir selecionada.");
                    // Código para excluir
                    break;
                case "A":
                    System.out.println("Informe o id: ");
                    int idAtualizado = scanner.nextInt();
                    System.out.println("Informe o valor da compra: ");
                    float valorAtualizado = scanner.nextFloat();
                    System.out.println("Informe o id do produto: ");
                    int erp_produtos_idAtualizado = scanner.nextInt();
                    System.out.println("Informe a quantidade: ");
                    int qtdAtualizado = scanner.nextInt();
                    System.out.println("Iforme o id da compra: ");
                    int erp_compras_idAtualizado = scanner.nextInt();
                    System.out.println("Iforme a data no formato YYYY-MM-DDThh, na iso 8601: ");
                    String data_lancamentoAtualizado = scanner.next();
                    Comprasitens novoitemAtualizado = new Comprasitens(valorAtualizado,
                            erp_produtos_idAtualizado,
                            qtdAtualizado,
                            erp_compras_idAtualizado,
                            data_lancamentoAtualizado);
                    Comprasitens novoAtualizado =  servicoApi.putComprasItens("http://localhost:3060/comprasitens",novoitemAtualizado,idAtualizado);
                    System.out.println("Novo item criado!");
                    System.out.println(novoAtualizado);
                    break;
                case "L":
                    System.out.println("Informe o limite: ");
                    int limite = scanner.nextInt();
                    System.out.println("Iforme a pagina: ");
                    int pagina = scanner.nextInt();

                    List<Comprasitens> paginada = servicoApi.getComprasItens(pagina,limite,"http://localhost:3060/comprasitens");

                    if(paginada == null){
                        System.out.println("Não foi possivel listar os itens verifique o limite e a pagina!");
                    }else {
                        for(Comprasitens item: paginada) {
                            System.out.println(item.toString());
                        }
                    }
                    break;

                case "LI":
                    System.out.println("Informe o id: ");
                    int id = scanner.nextInt();

                    Comprasitens item = servicoApi.getIdComprasItens("http://localhost:3060/comprasitens", id);

                    if(item == null){
                        System.out.println("Desculpe mas esse item não exite na database!");
                    }else{
                        System.out.println(item.toString());
                    }


                    break;
                default:
                    System.out.println("Opção inválida!");
                    // Código para tratar opções inválidas
                    break;

            }


        }

        // Gerar um número inteiro aleatório entre 0 e 99 (exclusivo)
//        int randomNumber = random.nextInt(2000);
//        ApiServices servicoApi = new ApiServices();
//
//        //Get paginado com limite
//
//        //Get por id
//
//        Comprasitens listadaPorId = servicoApi.getIdComprasItens("http://localhost:3060/comprasitens",id);
//
//        //Setando valor diferente e ataualizando
//        listadaPorId.setValor(randomNumber);
//        Comprasitens atualizada = servicoApi.putComprasItens("http://localhost:3060/comprasitens",listadaPorId, id);
//
//        //Criando novo elemento
//        //Pegando ids ja existentes
//        int comprasId = paginada.get(3).getErp_compras_id();
//        int produtosId = paginada.get(5).getErp_produtos_id();
//
//        //Criando a data no padrão iso
//        ZonedDateTime now = ZonedDateTime.now();
//        String iso8601Date = now.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
//
//        //Criando o objeto
//        ComprasitensPost novoElemento = new ComprasitensPost(300,produtosId,comprasId, randomNumber,iso8601Date);
//
//        Comprasitens apiCriado = servicoApi.postComprasItens("http://localhost:3060/comprasitens", novoElemento);
//
//
//        //Excluindo um elemento
//
//        Comprasitens elementoExcluido = servicoApi.deleteComprasItens("http://localhost:3060/comprasitens", apiCriado.getId());
//
//        System.out.println(paginada.get(9).toString());
//        System.out.println(listadaPorId.toString());
//        System.out.println(atualizada.toString());
//        System.out.println(apiCriado.toString());
//        System.out.println(elementoExcluido.toString());

    }
}