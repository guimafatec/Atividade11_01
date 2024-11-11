package br.edu.fateczl.atividade11_01.persistence;

import java.sql.SQLException;

public interface IJogadorDao {
    /*
     * @author: Gustavo Guimarães de Oliveira
     */
    public JogadorDao open() throws SQLException;
    public void close();
}
