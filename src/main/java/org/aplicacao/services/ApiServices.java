package org.aplicacao.services;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aplicacao.models.Comprasitens;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

import org.aplicacao.models.ComprasitensPost;
import org.aplicacao.models.ResponseList;
import org.aplicacao.models.ResponseObject;

public class ApiServices {


    @lombok.SneakyThrows
    private  HttpResponse<String> httpGet(String endereco) {
        HttpResponse<String> response = null;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .GET()
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }
    @lombok.SneakyThrows
    private  HttpResponse<String> httpPost(String endereco, String body) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .header("Content-Type", "application/json")
                .uri(URI.create(endereco))
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }

    @lombok.SneakyThrows
    private  HttpResponse<String> httpDelete(String endereco) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create(endereco))
                .header("Content-Type", "application/json")
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return response;

    }

    @lombok.SneakyThrows
    private  HttpResponse<String> httpPut(String endereco,String body) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(endereco))
                .header("Content-Type", "application/json")
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }




    public List<Comprasitens> getComprasItens(int pagina, int limite, String  endereco)  {
        List<Comprasitens> comprasitens = null;

        //Concatenando as query de limite
        String enderecoConcatenado = String.format("%s?pagina=%d&limite=%d", endereco ,pagina, limite);
        try {

            HttpResponse<String> response = httpGet(enderecoConcatenado);

            ObjectMapper mapper = new ObjectMapper();
            ResponseList responseList = mapper.readValue(response.body(), ResponseList.class);
            comprasitens = responseList.getData();

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao deserializar os elementos");
        }
            return comprasitens;
    }
    //Criando sobrecarga no java para endereco
    public List<Comprasitens> getComprasItens(String  endereco)  {
        List<Comprasitens> comprasitens = null;
        String enderecoConcatenado = String.format("%s?pagina=%d&limite=%d", endereco ,1, 10);
        try {

            HttpResponse<String> response = httpGet(enderecoConcatenado);

            ObjectMapper mapper = new ObjectMapper();
            ResponseList responseList = mapper.readValue(response.body(), ResponseList.class);
            comprasitens = responseList.getData();

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao deserializar os elementos");
        }
        return comprasitens;
    }

    public Comprasitens getIdComprasItens(String  endereco, int id)  {
        Comprasitens comprasitens = null;
        String enderecoConcatenado = String.format("%s/%d", endereco ,id);
        try {

            HttpResponse<String> response = httpGet(enderecoConcatenado);

            ObjectMapper mapper = new ObjectMapper();
            ResponseObject responseList = mapper.readValue(response.body(), ResponseObject.class);
            comprasitens = responseList.getData();

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao deserializar os elementos");
        }
        return comprasitens;
    }

    public Comprasitens postComprasItens(String  endereco, ComprasitensPost compra)  {
        Comprasitens comprasitens = null;
        try {
            //Serializando o objeto para enviar ao http
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(compra);

            //Fazendo a requisicao
            HttpResponse<String> response = httpPost(endereco,jsonData);
            //Lendo a resposta
            ResponseObject responseList = mapper.readValue(response.body(), ResponseObject.class);
            comprasitens = responseList.getData();

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao deserializar os elementos");
        }
        return comprasitens;
    }

    public Comprasitens putComprasItens(String  endereco, Comprasitens compra, int id)  {
        Comprasitens comprasitens = null;
        String enderecoConcatenado = String.format("%s/%d", endereco ,id);
        try {
            //Construindo o json convertendo de objeto para string
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(compra);

            //Chamando o metodo http para enviar o objeto
            HttpResponse<String> response = httpPut(enderecoConcatenado,jsonData);
            //Lendo a resposta
            ResponseObject responseList = mapper.readValue(response.body(), ResponseObject.class);

            //Atribuido o objeto
            comprasitens = responseList.getData();

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao deserializar os elementos no put erro: "+ e.getMessage());
        }
        return comprasitens;
    }
    public Comprasitens deleteComprasItens(String  endereco, int id)  {
        Comprasitens comprasitens = null;
        String enderecoConcatenado = String.format("%s/%d", endereco ,id);
        try {

            HttpResponse<String> response = httpDelete(enderecoConcatenado);

            ObjectMapper mapper = new ObjectMapper();
            ResponseObject responseList = mapper.readValue(response.body(), ResponseObject.class);
            comprasitens = responseList.getData();

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao deserializar os elementos");
        }
        return comprasitens;
    }
}
