package br.com.ebac.monopolioweb.controller;

import br.com.ebac.monopolioweb.domain.Cliente;
import br.com.ebac.monopolioweb.service.ClienteService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ClienteService clienteService;

    private Cliente cliente;
    private List<Cliente> clientes = new ArrayList<>();

    @PostConstruct
    public void iniciar() {
        novo();
        carregarClientes();
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void salvar() {
        try {
            clienteService.salvar(cliente);
            adicionarMensagem(FacesMessage.SEVERITY_INFO, "Cliente salvo com sucesso.");
            novo();
            carregarClientes();
        } catch (IllegalArgumentException ex) {
            adicionarMensagem(FacesMessage.SEVERITY_ERROR, ex.getMessage());
        }
    }

    public void editar(Cliente clienteSelecionado) {
        cliente = clienteSelecionado;
    }

    public void excluir(Cliente clienteSelecionado) {
        clienteService.excluir(clienteSelecionado);
        adicionarMensagem(FacesMessage.SEVERITY_INFO, "Cliente excluido com sucesso.");
        carregarClientes();
        novo();
    }

    private void carregarClientes() {
        clientes = clienteService.listar();
    }

    private void adicionarMensagem(FacesMessage.Severity severidade, String texto) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severidade, texto, null));
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
