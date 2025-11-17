# 🎟️ Sistema de Bilheteria – Java + MySQL

Este projeto é um sistema simples de bilheteria feito em Java, utilizando JDBC para conexão com MySQL.  
Ele permite realizar operações CRUD para **Usuários**, **Ingressos** e **Pedidos** através de um menu no console.

---

## 📌 Funcionalidades

### 👤 Usuários
- Criar
- Listar
- Atualizar
- Deletar  
Tipos permitidos: **comprador** e **organizador**

### 🎫 Ingressos
- Criar
- Listar
- Atualizar
- Deletar

### 🧾 Pedidos
- Criar
- Listar
- Atualizar status
- Deletar  
Status: **pendente**, **pago**, **cancelado**

---

## 🛠️ Tecnologias Utilizadas
- **Java 17+**
- **MySQL 8.x**
- **JDBC (MySQL Connector/J)**
- **Eclipse IDE**

---

## ▶️ Como Executar


```sql
1. Criar o banco de dados ou baixar:
CREATE DATABASE bilheteriaumj;
USE bilheteriaumj;
E acesse as tabelas no arquivo SQL do projeto.

2. Configurar a conexão no arquivo ConnectionFactory.java:

java
  private static final String URL = "jdbc:mysql://localhost:3306/bilheteriaumj";
  private static final String USER = "root";
  private static final String PASSWORD = "SUA_SENHA";

3. Adicionar o driver JDBC ao projeto
Baixe o MySQL Connector/J e adicione no Build Path do Eclipse.

4. Executar
Rode a classe:
 view/MainMenu.java

👥 Integrantes do Grupo
- Carlos Alves
- Jéssica Santos 
- João Lucas Lira
- Thayna Nayara
- Maryana Raphaely

Projeto desenvolvido para fins acadêmicos.
