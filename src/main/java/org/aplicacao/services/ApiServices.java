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
    private  HttpResponse<String> httpPost(String endereco, HttpRequest.BodyPublisher body) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .POST(body)
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;

    }

    @lombok.SneakyThrows
    private  HttpResponse<String> httpDelete(String endereco) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .DELETE()
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return response;

    }

    @lombok.SneakyThrows
    private  HttpResponse<String> httpPut(String endereco,HttpRequest.BodyPublisher body) {
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .PUT(body)
                .build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());


        return response;

    }




    public List<Comprasitens> getComprasItens(int pagina, int limite, String  endereco)  {
        List<Comprasitens> comprasitens = null;
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
    //Criando sobrecarga no java
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

    //Criando sobrecarga no java
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

    public Comprasitens postComprasItens(String  endereco, Comprasitens compra)  {
        Comprasitens comprasitens = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(compra);
            HttpRequest.BodyPublisher body  = HttpRequest.BodyPublishers.ofString(jsonData);

            HttpResponse<String> response = httpPost(endereco,body);
            ResponseObject responseList = mapper.readValue(response.body(), ResponseObject.class);
            comprasitens = responseList.getData();

        } catch (Exception e){
            System.out.println("Ocorreu um erro ao deserializar os elementos");
        }
        return comprasitens;
    }

    public List<Comprasitens> putComprasItens(String  endereco, Comprasitens compra, int id)  {
        List<Comprasitens> comprasitens = null;
        String enderecoConcatenado = String.format("%s/%d", endereco ,id);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonData = mapper.writeValueAsString(compra);
            HttpRequest.BodyPublisher body  = HttpRequest.BodyPublishers.ofString(jsonData);

            HttpResponse<String> response = httpPut(enderecoConcatenado,body);
            ResponseList responseList = mapper.readValue(response.body(), ResponseList.class);
            comprasitens = responseList.getData();
            throw new Exception(responseList.toString());

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
