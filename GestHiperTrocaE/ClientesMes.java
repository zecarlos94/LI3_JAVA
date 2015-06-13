import java.util.*;
import java.io.*;

/**
 * Classe que contém todas as informações mensais das compras
 */
public class ClientesMes implements Serializable
{
    /**
     * Variáveis de instância
     */
    private TreeSet<String> clientes;
    
    /**
     * Construtores
     */
    public ClientesMes() {
        this.clientes=new TreeSet<String>();
    }
    
    public ClientesMes(ClientesMes cm) {
        this.clientes=cm.getClientes();
    }
    
    /**
     * Getters
     */
    public TreeSet<String> getClientes() {
        return this.clientes;
    }
    
    /**
     * Setters
     */
    public void setClientes(TreeSet<String> clientes) {
        this.clientes=clientes;
    }
    
    /**
     * Clone
     */
    public ClientesMes clone() {
        return new ClientesMes(this);
    }
}
