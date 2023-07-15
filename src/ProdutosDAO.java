/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        try {
            conn = new conectaDAO().connectDB();
            String sql = "insert into produtos(nome,valor,status) values(?,?,?)";
            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (SQLException ex) {

        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        ProdutosDTO produto;
        try {
            conn = new conectaDAO().connectDB();
            String sql = "select * from produtos";
            Statement stmt = conn.createStatement();
            resultset=stmt.executeQuery(sql);
            while(resultset.next()){
                produto=new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
                
            }
        } catch (SQLException ex) {
            
        }
        return listagem;
    }
    public void venderProduto(int id){
        conn = new conectaDAO().connectDB();
        PreparedStatement ps = null;
        String sql = "update produtos set status = ? where id =?";
        
try {
    ps = conn.prepareStatement(sql);
    ps.setString(1,"Vendido");
    ps.setInt(2, id);
    ps.executeUpdate();
            
} catch ( SQLException sqle ) {
    System.out.println( "Erro no acesso ao Bando de Dados : "+ sqle.getMessage());
}
    }

}
