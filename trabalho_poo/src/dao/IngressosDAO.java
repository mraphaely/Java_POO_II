package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Ingresso;
import util.ConnectionFactory;

public class IngressosDAO {

    public void create(Ingresso ing) {
        String sql = "INSERT INTO ingressos (tipo, preco, quantidade_total, quantidade_disponivel) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ing.getTipo());
            stmt.setBigDecimal(2, ing.getPreco());
            stmt.setInt(3, ing.getQuantidadeTotal());
            stmt.setInt(4, ing.getQuantidadeDisponivel());

            stmt.executeUpdate();
            System.out.println("Ingresso criado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar ingresso: " + e.getMessage());
        }
    }

    public List<Ingresso> findAll() {
        List<Ingresso> lista = new ArrayList<>();
        String sql = "SELECT * FROM ingressos";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ingresso ing = new Ingresso();
                ing.setIdIngresso(rs.getInt("id_ingresso"));
                ing.setTipo(rs.getString("tipo"));
                ing.setPreco(rs.getBigDecimal("preco"));
                ing.setQuantidadeTotal(rs.getInt("quantidade_total"));
                ing.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                lista.add(ing);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar ingressos: " + e.getMessage());
        }

        return lista;
    }

    public Ingresso findById(int id) {
        String sql = "SELECT * FROM ingressos WHERE id_ingresso = ?";
        Ingresso ing = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                ing = new Ingresso();
                ing.setIdIngresso(rs.getInt("id_ingresso"));
                ing.setTipo(rs.getString("tipo"));
                ing.setPreco(rs.getBigDecimal("preco"));
                ing.setQuantidadeTotal(rs.getInt("quantidade_total"));
                ing.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar ingresso: " + e.getMessage());
        }

        return ing;
    }

    public void update(Ingresso ing) {
        String sql = "UPDATE ingressos SET tipo=?, preco=?, quantidade_total=?, quantidade_disponivel=? "
                   + "WHERE id_ingresso=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, ing.getTipo());
            stmt.setBigDecimal(2, ing.getPreco());
            stmt.setInt(3, ing.getQuantidadeTotal());
            stmt.setInt(4, ing.getQuantidadeDisponivel());
            stmt.setInt(5, ing.getIdIngresso());

            stmt.executeUpdate();
            System.out.println("Ingresso atualizado!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar ingresso: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM ingressos WHERE id_ingresso = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Ingresso removido!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar ingresso: " + e.getMessage());
        }
    }
}
