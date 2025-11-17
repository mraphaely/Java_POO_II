package view;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import dao.UsuarioDAO;
import dao.IngressosDAO;
import dao.PedidosDAO;

import model.Usuario;
import model.Ingresso;
import model.Pedido;

public class MainMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        IngressosDAO ingressoDAO = new IngressosDAO();
        PedidosDAO pedidoDAO = new PedidosDAO();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n====== MENU PRINCIPAL ======");
            System.out.println("1 - Usuários");
            System.out.println("2 - Ingressos");
            System.out.println("3 - Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            String entrada = sc.nextLine();
            if (entrada.isBlank()) continue;

            try {
                opcao = Integer.parseInt(entrada);
            } catch (Exception e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {

                case 1:
                    menuUsuarios(sc, usuarioDAO);
                    break;

                case 2:
                    menuIngressos(sc, ingressoDAO);
                    break;

                case 3:
                    menuPedidos(sc, pedidoDAO);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }

    private static void menuUsuarios(Scanner sc, UsuarioDAO usuarioDAO) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n---- CRUD USUÁRIOS ----");
            System.out.println("1 - Criar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            String entrada = sc.nextLine();

            try {
                opcao = Integer.parseInt(entrada);
            } catch (Exception e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {

                case 1:
                    criarUsuario(sc, usuarioDAO);
                    break;

                case 2:
                    listarUsuarios(usuarioDAO);
                    break;

                case 3:
                    atualizarUsuario(sc, usuarioDAO);
                    break;

                case 4:
                    deletarUsuario(sc, usuarioDAO);
                    break;
            }
        }
    }

    private static void criarUsuario(Scanner sc, UsuarioDAO usuarioDAO) {
        Usuario u = new Usuario();

        System.out.print("Nome: ");
        u.setNome(sc.nextLine());

        System.out.print("Email: ");
        u.setEmail(sc.nextLine());

        System.out.print("Senha: ");
        u.setSenhaHash(sc.nextLine());

        String tipo = "";
        while (!(tipo.equals("comprador") || tipo.equals("organizador"))) {
            System.out.print("Tipo (comprador/organizador): ");
            tipo = sc.nextLine().toLowerCase();
        }
        u.setTipoUsuario(tipo);

        u.setStatus(true);
        u.setDataCriacao(LocalDateTime.now());

        usuarioDAO.create(u);
    }

    private static void listarUsuarios(UsuarioDAO usuarioDAO) {
        List<Usuario> lista = usuarioDAO.findAll();

        System.out.println("\n-- Usuários --");

        if (lista.isEmpty()) {
            System.out.println("(nenhum usuário encontrado)");
            return;
        }

        for (Usuario u : lista) {
            System.out.println(u.getIdUsuario() + " - " + u.getNome() + " | " + u.getTipoUsuario());
        }
    }

    private static void atualizarUsuario(Scanner sc, UsuarioDAO usuarioDAO) {
        System.out.print("ID do usuário: ");
        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("ID inválido.");
            return;
        }

        Usuario u = usuarioDAO.findById(id);
        if (u == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Novo nome (" + u.getNome() + "): ");
        String nome = sc.nextLine();
        if (!nome.isBlank()) u.setNome(nome);

        System.out.print("Novo email (" + u.getEmail() + "): ");
        String email = sc.nextLine();
        if (!email.isBlank()) u.setEmail(email);

        System.out.print("Nova senha (enter para manter): ");
        String senha = sc.nextLine();
        if (!senha.isBlank()) u.setSenhaHash(senha);

        System.out.print("Tipo atual (" + u.getTipoUsuario() + "): ");
        String tipo = sc.nextLine().toLowerCase();
        if (!tipo.isBlank()) {
            if (tipo.equals("comprador") || tipo.equals("organizador")) {
                u.setTipoUsuario(tipo);
            } else {
                System.out.println("Tipo inválido, mantendo valor atual.");
            }
        }

        usuarioDAO.update(u);
    }

    private static void deletarUsuario(Scanner sc, UsuarioDAO usuarioDAO) {
        System.out.print("ID do usuário: ");

        try {
            int id = Integer.parseInt(sc.nextLine());
            usuarioDAO.delete(id);

        } catch (Exception e) {
            System.out.println("ID inválido.");
        }
    }

 
    private static void menuIngressos(Scanner sc, IngressosDAO ingressoDAO) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n---- CRUD INGRESSOS ----");
            System.out.println("1 - Criar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Deletar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {

                case 1:
                    criarIngresso(sc, ingressoDAO);
                    break;

                case 2:
                    listarIngressos(ingressoDAO);
                    break;

                case 3:
                    atualizarIngresso(sc, ingressoDAO);
                    break;

                case 4:
                    deletarIngresso(sc, ingressoDAO);
                    break;
            }
        }
    }

    private static void criarIngresso(Scanner sc, IngressosDAO ingressoDAO) {
        Ingresso ing = new Ingresso();

        System.out.print("Tipo do ingresso: ");
        ing.setTipo(sc.nextLine());

        System.out.print("Preço: ");
        try {
            ing.setPreco(new BigDecimal(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Valor inválido.");
            return;
        }

        System.out.print("Quantidade total: ");
        try {
            ing.setQuantidadeTotal(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Valor inválido.");
            return;
        }

        System.out.print("Quantidade disponível: ");
        try {
            ing.setQuantidadeDisponivel(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Valor inválido.");
            return;
        }

        ingressoDAO.create(ing);
    }

    private static void listarIngressos(IngressosDAO ingressoDAO) {
        List<Ingresso> lista = ingressoDAO.findAll();

        System.out.println("\n-- Ingressos --");

        if (lista.isEmpty())
            System.out.println("(nenhum ingresso encontrado)");

        for (Ingresso i : lista) {
            System.out.println(i.getIdIngresso() + " - " + i.getTipo() + " | R$" + i.getPreco());
        }
    }

    private static void atualizarIngresso(Scanner sc, IngressosDAO ingressoDAO) {
        System.out.print("ID do ingresso: ");
        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("ID inválido.");
            return;
        }

        Ingresso ing = ingressoDAO.findById(id);
        if (ing == null) {
            System.out.println("Ingresso não encontrado.");
            return;
        }

        System.out.print("Novo tipo (" + ing.getTipo() + "): ");
        String t = sc.nextLine();
        if (!t.isBlank()) ing.setTipo(t);

        System.out.print("Novo preço (" + ing.getPreco() + "): ");
        String p = sc.nextLine();
        if (!p.isBlank()) {
            try {
                ing.setPreco(new BigDecimal(p));
            } catch (Exception e) {
                System.out.println("Preço inválido.");
            }
        }

        System.out.print("Quantidade total (" + ing.getQuantidadeTotal() + "): ");
        String qt = sc.nextLine();
        if (!qt.isBlank()) {
            try {
                ing.setQuantidadeTotal(Integer.parseInt(qt));
            } catch (Exception e) {
                System.out.println("Quantidade inválida.");
            }
        }

        System.out.print("Quantidade disponível (" + ing.getQuantidadeDisponivel() + "): ");
        String qd = sc.nextLine();
        if (!qd.isBlank()) {
            try {
                ing.setQuantidadeDisponivel(Integer.parseInt(qd));
            } catch (Exception e) {
                System.out.println("Quantidade inválida.");
            }
        }

        ingressoDAO.update(ing);
    }

    private static void deletarIngresso(Scanner sc, IngressosDAO ingressoDAO) {
        System.out.print("ID do ingresso: ");

        try {
            ingressoDAO.delete(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("ID inválido.");
        }
    }


    private static void menuPedidos(Scanner sc, PedidosDAO pedidoDAO) {
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n---- CRUD PEDIDOS ----");
            System.out.println("1 - Criar");
            System.out.println("2 - Listar");
            System.out.println("3 - Atualizar Status");
            System.out.println("4 - Deletar");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Opção inválida.");
                continue;
            }

            switch (opcao) {

                case 1:
                    criarPedido(sc, pedidoDAO);
                    break;

                case 2:
                    listarPedidos(pedidoDAO);
                    break;

                case 3:
                    atualizarPedido(sc, pedidoDAO);
                    break;

                case 4:
                    deletarPedido(sc, pedidoDAO);
                    break;
            }
        }
    }

    private static void criarPedido(Scanner sc, PedidosDAO pedidoDAO) {
        Pedido p = new Pedido();

        System.out.print("ID do comprador: ");
        try {
            p.setIdComprador(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("ID inválido.");
            return;
        }

        String status = "";
        while (!(status.equals("pendente") || status.equals("pago") || status.equals("cancelado"))) {
            System.out.print("Status (pendente/pago/cancelado): ");
            status = sc.nextLine().toLowerCase();
        }
        p.setStatus(status);

        System.out.print("Valor total: ");
        try {
            p.setValorTotal(new BigDecimal(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("Valor inválido.");
            return;
        }

        p.setDataPedido(LocalDateTime.now());

        pedidoDAO.create(p);
    }

    private static void listarPedidos(PedidosDAO pedidoDAO) {
        List<Pedido> lista = pedidoDAO.findAll();

        System.out.println("\n-- Pedidos --");

        if (lista.isEmpty())
            System.out.println("(nenhum pedido encontrado)");

        for (Pedido p : lista) {
            System.out.println(
                p.getIdPedido() + " - comprador=" + p.getIdComprador()
                + " | status=" + p.getStatus()
                + " | total=R$" + p.getValorTotal()
            );
        }
    }

    private static void atualizarPedido(Scanner sc, PedidosDAO pedidoDAO) {
        System.out.print("ID do pedido: ");
        int id;

        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("ID inválido.");
            return;
        }

        Pedido p = pedidoDAO.findById(id);
        if (p == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        String status = "";
        while (!(status.equals("pendente") || status.equals("pago") || status.equals("cancelado"))) {
            System.out.print("Novo status (pendente/pago/cancelado): ");
            status = sc.nextLine().toLowerCase();
        }
        p.setStatus(status);

        System.out.print("Novo valor (enter para manter): ");
        String v = sc.nextLine();
        if (!v.isBlank()) {
            try {
                p.setValorTotal(new BigDecimal(v));
            } catch (Exception e) {
                System.out.println("Valor inválido.");
            }
        }

        pedidoDAO.update(p);
    }

    private static void deletarPedido(Scanner sc, PedidosDAO pedidoDAO) {
        System.out.print("ID do pedido: ");

        try {
            pedidoDAO.delete(Integer.parseInt(sc.nextLine()));
        } catch (Exception e) {
            System.out.println("ID inválido.");
        }
    }

	public static void displayMenu() {
		// TODO Auto-generated method stub
		
	}

	}

