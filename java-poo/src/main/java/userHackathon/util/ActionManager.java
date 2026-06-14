package userHackathon.util;

import userHackathon.model.Empresa;
import userHackathon.service.EmpresaService;

import javax.swing.*;

public class ActionManager {
    public static void configurarBotaoStatusEmpresa(
            JButton botao,
            JTextField idField,
            Boolean novoStatus,
            EmpresaService service,
            JTable tabela,
            //vai guardar a informação e após a execução da ação ele vai executar oque está guardado dentro dele
            Runnable atualizarTela
    ){
        botao.addActionListener(e -> {
            //.trim é apenas para tirar espaços e pegar apenas a String
            String idTexto = idField.getText().trim();
            if (!idTexto.isEmpty()){
                try{
                    Long id = Long.valueOf(idTexto);

                    Empresa empresa = new Empresa();
                    empresa.setId(id);
                    empresa.setStatus(novoStatus);

                    service.atualizar(empresa);

                    //vai executar a limpeza feita na GUI
                    atualizarTela.run();

                } catch (Exception ex) {
                    System.out.println("Error" + ex.getMessage());
                }
            }else{
                //JOptionPane funciona para poder criar mensagens que apareçam informando algo para o usuario
                JOptionPane.showMessageDialog(null,"Selecione uma empresa primeiro",
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
