import java.util.*;
import java.io.*;

public class InfoCliente implements Serializable
{
    private String codigo;
    private int quantidade;
    
    public InfoCliente() {
        this.codigo="N/A";
        this.quantidade=0;
    }
    
    public InfoCliente(InfoCliente p) {
        this.codigo=p.getCodigo();
        this.quantidade=p.getQuantidade();
    }
    
    public InfoCliente(String codigo, int quantidade) {
        this.codigo=codigo;
        this.quantidade=quantidade;
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void setCodigo(String codigo) {
        this.codigo=codigo;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade=quantidade;
    }
    
    public InfoCliente clone() {
        return new InfoCliente(this);
    }
}
