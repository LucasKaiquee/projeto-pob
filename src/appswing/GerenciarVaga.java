package appswing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Recrutador;
import modelo.Vaga;
import regras_negocio.Fachada;

public class GerenciarVaga {

    private Recrutador recrutador;

    public Recrutador getRecrutador(){
        return this.recrutador;
    }

    public GerenciarVaga(Recrutador recrutador) {

        this.recrutador = recrutador;
        
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

        // JLabel lblRecrutador = new JLabel("Recrutador:");
        // JTextField txtRecrutador = new JTextField();

        JButton btnAdicionar = new JButton(recrutador.getVagaGerenciada() == null ? "Adicionar Vaga" : "Atualizar Vaga");
        JButton btnExcluir = new JButton("Excluir Vaga");

        // Adicionar componentes ao painel de entrada
        inputPanel.add(lblDescricao);
        inputPanel.add(txtDescricao);
        inputPanel.add(lblSalario);
        inputPanel.add(txtSalario);
        inputPanel.add(lblArea);
        inputPanel.add(txtArea);
        inputPanel.add(lblRequisitos);
        inputPanel.add(txtRequisitos);
        // inputPanel.add(lblRecrutador);
        // inputPanel.add(txtRecrutador);

        // Populando a tabela se existir vaga gerenciada
        if (recrutador.getVagaGerenciada() != null) {
            Vaga vaga = recrutador.getVagaGerenciada();
            tableModel.addRow(new Object[]{
                vaga.getId(),
                vaga.getDescricao(),
                vaga.getSalario(),
                vaga.getArea(),
                vaga.getRecrutador().getNome()
            });
        }

        // Ação do botão "Adicionar/Atualizar Vaga"
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String descricao = txtDescricao.getText().trim();
                Double salario = null;
                String area = txtArea.getText().trim();
                String requisitos = txtRequisitos.getText().trim();
                List<String> listaRequisitos = requisitos.isEmpty() ? null : List.of(requisitos.split(","));

                try {
                    if (!txtSalario.getText().trim().isEmpty()) {
                        salario = Double.parseDouble(txtSalario.getText().trim());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Salário inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    if (recrutador.getVagaGerenciada() == null) {
                        // Criar nova vaga
                        Fachada.criarVaga(descricao, salario, area, listaRequisitos, recrutador);
                        JOptionPane.showMessageDialog(frame, "Vaga criada com sucesso!");
                    } else {
                        // Alterar vaga existente
                        int id = recrutador.getVagaGerenciada().getId();
                        Fachada.alterarVaga(id,
                            descricao.isEmpty() ? null : descricao,
                            salario,
                            area.isEmpty() ? null : area,
                            listaRequisitos);
                        JOptionPane.showMessageDialog(frame, "Vaga atualizada com sucesso!");
                    }
                } catch (Exception ex) {
                    // JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println(ex.getMessage());
                }

                // Atualizar tabela
                tableModel.setRowCount(0);
                if (recrutador.getVagaGerenciada() != null) {
                    Vaga vaga = recrutador.getVagaGerenciada();
                    tableModel.addRow(new Object[]{
                        vaga.getId(),
                        vaga.getDescricao(),
                        vaga.getSalario(),
                        vaga.getArea(),
                        vaga.getRecrutador().getNome()
                    });
                }

                // Limpar campos
                txtDescricao.setText("");
                txtSalario.setText("");
                txtArea.setText("");
                txtRequisitos.setText("");

                // Atualizar texto do botão
                btnAdicionar.setText(recrutador.getVagaGerenciada() == null ? "Adicionar Vaga" : "Atualizar Vaga");
            }
        });

        // Ação do botão "Excluir Vaga"
        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (recrutador.getVagaGerenciada() == null) {
                    JOptionPane.showMessageDialog(frame, "Nenhuma vaga selecionada para excluir!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int id = recrutador.getVagaGerenciada().getId();
                    Fachada.removerVaga(id);
                    JOptionPane.showMessageDialog(frame, "Vaga excluída com sucesso!");

                    // Atualizar tabela e limpar vaga gerenciada
                    tableModel.setRowCount(0);
                    recrutador.setVagaGerenciada(null);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    System.out.println(ex.getMessage());
                    System.out.println(ex.getCause());
                    System.out.println(ex.getStackTrace());
                }
            }
        });

        // Adicionar componentes ao painel principal
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        buttonPanel.add(btnAdicionar);
        buttonPanel.add(btnExcluir);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(inputPanel, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Adicionar o painel ao JFrame
        frame.add(panel);

        // Tornar a janela visível
        frame.setVisible(true);
    }
}
