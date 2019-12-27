/*
 * Este programa é uma demonstração de como usar o banco de dados 
 * SQLITE nas operações de SELECT, INSEÇÃO, ATUALIZAÇÃO e REMOÇÃO
 */
package javasql;

/**
 *  pacote Java para trabalhar com SQL
 * 
 */
import java.sql.*;

public class JavaSql
{
  Connection conn; // Declaração de um conector para trabalhar com Bancos de dados

  public static void main(String[] args)
  {
     // Instância da classe JavaSql
      new JavaSql();
  }
 
  public JavaSql()
  {
    try
    {
        // Criação da instância para o banco de dados SQLITE
        Class.forName("org.sqlite.JDBC").newInstance();
      
        ///Local onde o Banco de dados se encontra
        String localDataBase = "C:\\Users\\Care\\Documents";
        /// Nome do Banco de dados criado
        String dataBaseName = "Cafes.db";
        /// determinação da URL para conexão do banco de dados
        String url = "jdbc:sqlite:"+localDataBase+dataBaseName;
     // Cria a conexão com o com o banco de DADOS  
      conn = DriverManager.getConnection(url);
      // imprime a mensagen que a conexão foi realizada com sucesso
      System.out.println("Conexao realizada com Sucesso");
      // Realização dos testes 
      doTests();
     // sempre o que o banco de dados for aberto e ter operações realizadas 
    // ele precisa ser fechado.
      conn.close();
    } // Captura as excessões se operação não for bem sucedida
    catch (ClassNotFoundException |
            IllegalAccessException |
            InstantiationException |
            SQLException ex) 
    {
        // Imprime a informação de falha para o programador ou usuário.
        System.err.println(ex.getMessage());
    }
  }

  private void doTests()
  {
    // Realiza a operação de select no Banco de Dados
    doSelectTest();

    // Realiza a operação de inserção no Banco de Dados
    doInsertTest();  doSelectTest();
    // Realiza a operação de Atualização no Banco de Dados
    doUpdateTest();  doSelectTest();
  }

  private void doSelectTest()
  {
    System.out.println("[OUTPUT FROM SELECT]");
    // Query que será executada dentro do banco de dados
    String query = "SELECT CAFE_NOME, VALOR FROM Cafes";
    try
    {
        // captura a conexão para criar um Statment
        Statement st = conn.createStatement();
        
        // Executa a Query dentro do banco de dados e retorna a informação no objeto rs
        ResultSet rs = st.executeQuery(query);
      
        // Reliza a verredura das informações que foram extraidas do banco de dados
        while (rs.next())
            {
                String s = rs.getString("CAFE_NOME");
                double n = rs.getFloat("VALOR");
                System.out.println(s + "   " + n);
            }
    } //captura execeção caso algum problema ocorra com instruções SQL
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
  }

  private void doInsertTest()
  {
    System.out.print("\n[Performing INSERT] ..   . ");
    String query = "INSERT INTO CAFES(CAFE_NOME,VALOR,VENDAS,TOTAL)" +
                       "VALUES ('BREAKFAST BLEND', 7.99, 0, 0)";
    try
    {
      
        //Criação do Statment atravé da conexão com o banco de dados
        Statement st = conn.createStatement();
        // executa a query de inserção de dados no banco de dados
        st.executeUpdate(query);
    }  //captura execeção caso algum problema ocorra com instruções SQL
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
  }

  private void doUpdateTest()
  {
    System.out.print("\n[Performing UPDATE] ... ");
    //definição da Query que será executada no Banco de dados
    String query = "UPDATE Cafes SET Valor=4.99 WHERE CAFE_NOME='BREAKFAST BLEND'";
    try
    {
      //Criação do Statment atravé da conexão com o banco de dados
      Statement st = conn.createStatement();
      // executa a query de inserção de dados no banco de dados
      st.executeUpdate(query);
    }//captura execeção caso algum problema ocorra com instruções SQL
    catch (SQLException ex)
    {
      System.err.println(ex.getMessage());
    }
  }
}
