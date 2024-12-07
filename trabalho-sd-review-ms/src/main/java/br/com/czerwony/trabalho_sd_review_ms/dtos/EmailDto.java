package br.com.czerwony.trabalho_sd_review_ms.dtos;

import java.util.UUID;

public class EmailDto {
    private UUID id;
    private String emailPara;
    private String assunto;
    private String texto;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmailPara() {
        return emailPara;
    }

    public void setEmailPara(String emailPara) {
        this.emailPara = emailPara;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
