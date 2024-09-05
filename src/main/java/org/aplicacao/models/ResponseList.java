package org.aplicacao.models;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseList {
        private List<Comprasitens> data;
        private  int code;
        private boolean error;
        private String message;
        private int total;
        private int limite;
        private int total_paginas;
        private int pagina;


    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public List<Comprasitens> getData() {
        return data;
    }

    public void setData(List<Comprasitens> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public int getTotal_paginas() {
        return total_paginas;
    }

    public void setTotal_paginas(int total_paginas) {
        this.total_paginas = total_paginas;
    }


    @Override
    public String toString() {
        return "ResponseList{" +
                "data=" + data +
                ", code=" + code +
                ", error=" + error +
                ", message='" + message + '\'' +
                ", total=" + total +
                ", limite=" + limite +
                ", total_paginas=" + total_paginas +
                ", pagina=" + pagina +
                '}';
    }
}
