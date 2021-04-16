package com.vendas.sistema.teste.repository;

import com.vendas.sistema.teste.repositories.VendaRepositoryCustomImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

@DataJpaTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:/scripts/tests_vendas.sql"})
public class VendaRepositoryCustomImplTest {

    @Autowired
    private VendaRepositoryCustomImpl vendaRepositoryCustomImpl;

    @Test
    public void findVendasVendedorPorPeriodoAndMedia() {
        assertThat(vendaRepositoryCustomImpl.findAllByDateBetween("2021-04-1001:00", "2021-04-1023:50"))
                .extracting(i -> tuple(i.get(0, String.class), i.get(1, Long.class)))
                .containsExactlyInAnyOrder(tuple("JOSE VENDEDOR PARA TESTE", 4L));
    }
}