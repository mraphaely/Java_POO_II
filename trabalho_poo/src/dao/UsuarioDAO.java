package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;
import util.ConnectionFactory;

public class UsuarioDAO {

    public void create(Usuario u) {
        String sql = "INSERT INTO usuarios (nome, email, senha_hash, tipo_usuario, status, data_criacao) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenhaHash());
            stmt.setString(4, u.getTipoUsuario());
            stmt.setBoolean(5, u.isStatus());
            stmt.setTimestamp(6, Timestamp.valueOf(u.getDataCriacao()));

            stmt.executeUpdate();

            System.out.println("Usuário criado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao criar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> findAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenhaHash(rs.getString("senha_hash"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                u.setStatus(rs.getBoolean("status"));
                u.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());

                lista.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
        }

        return lista;
    }

    public Usuario findById(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        Usuario u = null;

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenhaHash(rs.getString("senha_hash"));
                u.setTipoUsuario(rs.getString("tipo_usuario"));
                u.setStatus(rs.getBoolean("status"));
                u.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return u;
    }

    public void update(Usuario u) {
        String sql = "UPDATE usuarios SET nome=?, email=?, senha_hash=?, tipo_usuario=?, status=? "
                   + "WHERE id_usuario=?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenhaHash());
            stmt.setString(4, u.getTipoUsuario());
            stmt.setBoolean(5, u.isStatus());
            stmt.setInt(6, u.getIdUsuario());

            stmt.executeUpdate();

            System.out.println("Usuário atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Usuário removido com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
        }
    }
}
