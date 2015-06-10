import java.util.*;
import java.io.*;

public class InfoProduto implements Serializable
{
    private String codigo;
    private int quantidade;
    
    public InfoProduto() {
        this.codigo="N/A";
        this.quantidade=0;
    }
    
    public InfoProduto(InfoProduto p) {
        this.codigo=p.getCodigo();
        this.quantidade=p.getQuantidade();
    }
    
    public InfoProduto(String codigo, int quantidade) {
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
    
    public InfoProduto clone() {
        return new InfoProduto(this);
    }
}
