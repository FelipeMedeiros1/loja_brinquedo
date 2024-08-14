package loja_brinquedo.infra.exception;

public class LojaException extends RuntimeException{
    public LojaException(String mensagem){
        super(mensagem);
    }
}
