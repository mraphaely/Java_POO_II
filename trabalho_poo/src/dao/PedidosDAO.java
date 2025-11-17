package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Pedido;
import util.ConnectionFactory;

public class PedidosDAO {

    public void create(Pedido p) {
        String sql = "INSERT INTO pedidos (id_comprador, status, valor_total, data_pedido) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getIdComprador());
            stmt.setString(2, p.getStatus());
            stmt.setBigDecimal(3, p.getValorTotal());
            stmt.setTimestamp(4, Timestamp.valueOf(p.getDataPedido()));

            stmt.executeUpdate();

            System.out.println("Pedido criado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
        }
    }

    public List<Pedido> findAll() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdComprador(rs.getInt("id_comprador"));
                p.setStatus(rs.getString("status"));
                p.setValorTotal(rs.getBigDecimal("valor_total"));
                p.setDataPedido(rs.getTimestamp("data_pedido").toLocalDateTime());

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    public Pedido findById(int id) {
        String sql = "SELECT * FROM pedidos WHERE id_pedido = ?";
        Pedido p = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdComprador(rs.getInt("id_comprador"));
                p.setStatus(rs.getString("status"));
                p.setValorTotal(rs.getBigDecimal("valor_total"));
                p.setDataPedido(rs.getTimestamp("data_pedido").toLocalDateTime());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pedido: " + e.getMessage());
        }

        return p;
    }

    public void update(Pedido p) {
        String sql = "UPDATE pedidos SET status=?, valor_total=? WHERE id_pedido=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getStatus());
            stmt.setBigDecimal(2, p.getValorTotal());
            stmt.setInt(3, p.getIdPedido());

            stmt.executeUpdate();

            System.out.println("Pedido atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pedido: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM pedidos WHERE id_pedido = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Pedido removido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar pedido: " + e.getMessage());
        }
    }
}
