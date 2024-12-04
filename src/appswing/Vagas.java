package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import daodb4o.DAO;
import modelo.Vaga;
import regras_negocio.Fachada;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Vagas {
    public Vagas() {

        
        // Criar o JFrame
        JFrame frame = new JFrame("ContratAe - Gerenciamento de Vagas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Tabela de vagas
        String[] colunas = {"ID", "Descrição", "Salário", "Área", "Recrutador"};
        DefaultTableModel tableModel = new DefaultTableModel(colunas, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Painel de entrada para adicionar vaga
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblDescricao = new JLabel("Descrição:");
        JTextField txtDescricao = new JTextField();

        JLabel lblSalario = new JLabel("Salário:");
        JTextField txtSalario = new JTextField();

        JLabel lblArea = new JLabel("Área:");
        JTextField txtArea = new JTextField();

        JLabel lblRequisitos = new JLabel("Requisitos:");
        JTextField txtRequisitos = new JTextField();

        JLabel lblRecrutador = new JLabel("Recrutador:");
        JTextField txtRecrutador = new JTextField();

        JButton btnAdicionar = new JButton("Adicionar Vaga");

        // Adicionar componentes ao painel de entrada
        inputPanel.add(lblDescricao);
        inputPanel.add(txtDescricao);
        inputPanel.add(lblSalario);
        inputPanel.add(txtSalario);
        inputPanel.add(lblArea);
        inputPanel.add(txtArea);
        inputPanel.add(lblRequisitos);
        inputPanel.add(txtRequisitos);
        inputPanel.add(lblRecrutador);
        inputPanel.add(txtRecrutador);
        for(Vaga v : Fachada.listarVagas()){
            System.out.println(v);
            // int id = Fachada.listarVagas().get(Fachada.listarVagas().size()-1).getId();
            tableModel.addRow(new Object[]{v.getId(), v.getDescricao(), v.getSalario(), v.getArea(), v.getRecrutador().getNome()});
        }

        // Adicionar ação ao botão "Adicionar Vaga"
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                


                String descricao = txtDescricao.getText().trim();
                double salario = Double.parseDouble(txtSalario.getText().trim());
                String area = txtArea.getText().trim();
                String requisitos = txtRequisitos.getText().trim();
                String recrutador = txtRecrutador.getText().trim();

                 String[] palavras = requisitos.split(","); // Separa a string por vírgulas
                ArrayList<String> Myrequisitos = new ArrayList<>();

                // Adiciona cada palavra à lista
                for (String palavra : palavras) {
                    Myrequisitos.add(palavra);
                }

                if (descricao.isEmpty() || area.isEmpty() || requisitos.isEmpty() || recrutador.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Adicionar vaga à tabela
                    DAO.begin();
                    try {
                        Fachada.criarVaga(descricao, salario, area, Myrequisitos, Fachada.localizarRecrutador(recrutador));

                        int id = Fachada.listarVagas().get(Fachada.listarVagas().size()-1).getId();
                        tableModel.addRow(new Object[]{id, descricao, salario, area, recrutador});
                    } catch (Exception ae) {
                        System.out.println(ae.getMessage());
                    }
                    DAO.commit();

                    // Limpar os campos
                    txtDescricao.setText("");
                    txtSalario.setText("");
                    txtArea.setText("");
                    txtRequisitos.setText("");
                    txtRecrutador.setText("");

                    JOptionPane.showMessageDialog(frame, "Vaga adicionada com sucesso!");
                }
            }
        });

        // Adicionar componentes ao painel principal
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);

        // Adicionar o botão abaixo do painel
        panel.add(btnAdicionar, BorderLayout.NORTH);

        // Adicionar o painel ao JFrame
        frame.add(panel);

        // Tornar a janela visível
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Vagas();
    }
}
