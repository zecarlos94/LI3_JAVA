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
    private HashSet<String> clientes;
    
    /**
     * Construtores
     */
    public ClientesMes() {
        this.clientes=new HashSet<String>();
    }
    
    public ClientesMes(ClientesMes cm) {
        this.clientes=cm.getClientes();
    }
    
    /**
     * Getters
     */
    public HashSet<String> getClientes() {
        return this.clientes;
    }
    
    /**
     * Setters
     */
    public void setClientes(HashSet<String> clientes) {
        this.clientes=clientes;
    }
    
    /**
     * Clone
     */
    public ClientesMes clone() {
        return new ClientesMes(this);
    }
}