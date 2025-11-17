package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {

    private int idPedido;
    private int idComprador;
    private LocalDateTime dataPedido;
    private String status;
    private BigDecimal valorTotal;

    public Pedido() {}

    public Pedido(int idPedido, int idComprador, LocalDateTime dataPedido,
                  String status, BigDecimal valorTotal) {
        this.idPedido = idPedido;
        this.idComprador = idComprador;
        this.dataPedido = dataPedido;
        this.status = status;
        this.valorTotal = valorTotal;
    }

    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdComprador() { return idComprador; }
    public void setIdComprador(int idComprador) { this.idComprador = idComprador; }

    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}
