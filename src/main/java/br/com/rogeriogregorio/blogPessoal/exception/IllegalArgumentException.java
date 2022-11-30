package br.com.rogeriogregorio.blogPessoal.exception;

public class IllegalArgumentException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public IllegalArgumentException() {
        super("Invalid argument");
    }
}