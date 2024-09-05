package org.aplicacao.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Comprasitens {

    private int id;
    private float valor;
    private int erp_produtos_id;
    private int qtd;
    private int erp_compras_id;
    private String data_lancamento;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getErp_produtos_id() {
        return erp_produtos_id;
    }

    public void setErp_produtos_id(int erp_produtos_id) {
        this.erp_produtos_id = erp_produtos_id;
    }

    public int getErp_compras_id() {
        return erp_compras_id;
    }

    public void setErp_compras_id(int erp_compras_id) {
        this.erp_compras_id = erp_compras_id;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }
    @Override
    public String toString() {
        return "Comprasitens{" +
                "id=" + id +
                ", valor=" + valor +
                ", erp_produtos_id=" + erp_produtos_id +
                ", qtd=" + qtd +
                ", erp_compras_id=" + erp_compras_id +
                ", data_lancamento='" + data_lancamento + '\'' +
                '}';
    }

}
