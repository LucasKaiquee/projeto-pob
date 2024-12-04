package appswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import regras_negocio.Fachada;

public class TelaMain {
    public static void main(String[] args) {

        Fachada.inicializar();
        // Criação do JFrame (janela principal)
        JFrame frame = new JFrame("ContratAe - Sistema de Vagas de Emprego");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Label com a pergunta
        JLabel questionLabel = new JLabel("Qual tipo de perfil você deseja entrar?", JLabel.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(questionLabel, BorderLayout.NORTH);

        // Painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Botão para Candidato
        JButton candidateButton = new JButton("Candidato");
        candidateButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonPanel.add(candidateButton);

        // Botão para Recrutador
        JButton recruiterButton = new JButton("Recrutador");
        recruiterButton.setFont(new Font("Arial", Font.PLAIN, 14));
        buttonPanel.add(recruiterButton);

        // Adicionando o painel de botões ao painel principal
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Adicionando ações aos botões
        candidateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // JOptionPane.showMessageDialog(frame, "Você escolheu o perfil Candidato.");
                new InicialCandidato();
                // Aqui você pode abrir a tela específica do Candidato
            }
        });

        recruiterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InicialRecrutador();
                // JOptionPane.showMessageDialog(frame, "Você escolheu o perfil Recrutador.");
                // Aqui você pode abrir a tela específica do Recrutador
            }
        });

        // Adicionando o painel principal ao JFrame
        frame.add(panel);

        // Tornando o JFrame visível
        frame.setVisible(true);
    }
}
