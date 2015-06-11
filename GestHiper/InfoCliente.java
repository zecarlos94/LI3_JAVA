import java.util.*;
import java.io.*;

public class InfoCliente implements Serializable
{
    private String codigo;
    private int quantidade;
    private int mes;
    private double preco_produto;
    private String modo;
    
    public InfoCliente() {
        this.codigo="N/A";
        this.quantidade=0;
        this.mes=1;
        this.preco_produto=0.0;
        this.modo="N";
    }
    
    public InfoCliente(InfoCliente p) {
        this.codigo=p.getCodigo();
        this.quantidade=p.getQuantidade();
        this.mes=p.getMes();
        this.preco_produto=p.getPrecoProduto();
        this.modo=p.getModo();
    }
    
    public InfoCliente(String codigo, int quantidade, int mes, double preco_produto, String modo) {
        this.codigo=codigo;
        this.quantidade=quantidade;
        this.mes=mes;
        this.preco_produto=preco_produto;
        this.modo=modo;
    }
    
    public InfoCliente(String codigo, int quantidade) {
        this.codigo=codigo;
        this.quantidade=quantidade;
        this.mes=1;
        this.preco_produto=0.0;
        this.modo="N";
    }
    
    public String getCodigo() {
        return this.codigo;
    }
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public int getMes() {
        return this.mes;
    }
    
    public double getPrecoProduto() {
        return this.preco_produto;
    }
    
    public String getModo() {
        return this.modo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo=codigo;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade=quantidade;
    }
    
    public void setMes(int mes) {
        this.mes=mes;
    }
    
    public void setPrecoProduto(double preco_produto) {
        this.preco_produto=preco_produto;
    }
    
    public void setModo(String modo) {
        this.modo=modo;
    }
    
    public InfoCliente clone() {
        return new InfoCliente(this);
    }
}
