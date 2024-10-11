
package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import br.com.Views.TelaPrincipal;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class UsuarioDAO {

Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void logar(UsuarioDTO objusuarioDTO) {
        String sql = "select * from tb_usuarios where usuario = ? and senha = ?";
        conexao = ConexaoDAO.conector();

        try {
            // preparar a consulta no banco, em função ao que foi inserido nas caixas de texto
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objusuarioDTO.getLogin_usuario());
            pst.setString(2, objusuarioDTO.getSenha_usuario());

//            executa a query
            rs = pst.executeQuery();
//            verifica se existe usuario
            if (rs.next()) {
                // obtem o conteúdo do atributo perfil
                String perfil = rs.getString(3);
//                System.out.println(perfil);

                //tratamento de perfil
                if (perfil.equals("admin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.subMenuUsuario.setEnabled(true);
                    conexao.close();//Fechar a conexão                    
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    conexao.close();//Fechar a conexão   

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha invalidos");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "* Metodo Logar **" + e);
        }
    }
}