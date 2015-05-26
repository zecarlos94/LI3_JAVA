import java.util.*;
import java.io.*;

/**
 * Classe que guarda todas as compras válidas efetuadas
 */
public class Compra implements Serializable
{
    private String codigo_produto;
    private double preco;
    private int quantidade_comprada;
    private String modo;
    private String codigo_cliente;
    private int mes;
    
    public Compra() {
        this.codigo_produto="N/A";
        this.preco=0;
        this.quantidade_comprada=0;
        this.modo="N/A";
        this.codigo_cliente="N/A";
        this.mes=0;
    }
    
    public Compra(String codigo_produto, double preco, int quantidade_comprada, String modo, String codigo_cliente, int mes) {
        this.codigo_produto=codigo_produto;
        this.preco=preco;
        this.quantidade_comprada=quantidade_comprada;
        this.modo=modo;
        this.codigo_cliente=codigo_cliente;
        this.mes=mes;
    }
    
    public Compra(Compra c) {
        this.codigo_produto=c.getCodigoProduto();
        this.preco=c.getPreco();
        this.quantidade_comprada=c.getQuantidadeComprada();
        this.modo=c.getModo();
        this.codigo_cliente=c.getCodigoCliente();
        this.mes=c.getMes();
    }
    
    public String getCodigoProduto() {return  this.codigo_produto;}
    public double getPreco() {return this.preco;}
    public int getQuantidadeComprada() {return this.quantidade_comprada;}
    public String getModo() {return this.modo;}
    public String getCodigoCliente() {return this.codigo_cliente;}
    public int getMes() {return this.mes;}
    
    public void setCodigoProduto(String codigo_produto) {this.codigo_produto=codigo_produto;}
    public void setPreco(double preco) {this.preco=preco;}
    public void setQuantidadeComprada(int quantidade_comprada) {this.quantidade_comprada=quantidade_comprada;}
    public void setModo(String modo) {this.modo=modo;}
    public void setCodigoCliente(String codigo_cliente) {this.codigo_cliente=codigo_cliente;}
    public void setMes(int mes) {this.mes=mes;}
    
    public boolean equals(Compra c) {
        if(this.codigo_produto.equals(c.getCodigoProduto()) && this.preco==c.getPreco() && this.quantidade_comprada==c.getQuantidadeComprada() &&
        this.modo.equals(c.getModo()) && this.codigo_cliente.equals(getCodigoCliente()) && this.mes==c.getMes()) return true;
        else return false;
    }
    
    public String toString() {
        return new String(this.codigo_produto+this.preco+this.quantidade_comprada+this.modo+this.codigo_cliente+this.mes);
    }
    
    public Compra clone() {
        return new Compra(this);
    }
}
