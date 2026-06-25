package br.com.ebac.monopolioweb.dao;

import br.com.ebac.monopolioweb.domain.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteDAO {

    @PersistenceContext(unitName = "MonopolioWebPU")
    private EntityManager entityManager;

    public Cliente salvar(Cliente cliente) {
        if (cliente.isNovo()) {
            entityManager.persist(cliente);
            return cliente;
        }
        return entityManager.merge(cliente);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return Optional.ofNullable(entityManager.find(Cliente.class, id));
    }

    public List<Cliente> listar() {
        return entityManager
                .createQuery("select c from Cliente c order by c.nome", Cliente.class)
                .getResultList();
    }

    public boolean existeCpf(String cpf, Long idIgnorado) {
        Long total = entityManager
                .createQuery("""
                        select count(c)
                        from Cliente c
                        where c.cpf = :cpf
                          and (:idIgnorado is null or c.id <> :idIgnorado)
                        """, Long.class)
                .setParameter("cpf", cpf)
                .setParameter("idIgnorado", idIgnorado)
                .getSingleResult();
        return total > 0;
    }

    public void excluir(Cliente cliente) {
        Cliente gerenciado = entityManager.contains(cliente)
                ? cliente
                : entityManager.merge(cliente);
        entityManager.remove(gerenciado);
    }
}
