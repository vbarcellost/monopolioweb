package br.com.ebac.monopolioweb.service;

import br.com.ebac.monopolioweb.dao.ClienteDAO;
import br.com.ebac.monopolioweb.domain.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClienteService {

    @Inject
    private ClienteDAO clienteDAO;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        if (clienteDAO.existeCpf(cliente.getCpf(), cliente.getId())) {
            throw new IllegalArgumentException("Ja existe um cliente cadastrado com este CPF.");
        }
        return clienteDAO.salvar(cliente);
    }

    public List<Cliente> listar() {
        return clienteDAO.listar();
    }

    @Transactional
    public void excluir(Cliente cliente) {
        clienteDAO.excluir(cliente);
    }
}
