package appswing;

// import java.awt.EventQueue;
// import java.awt.Font;
// import java.awt.Image;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

// import javax.swing.ImageIcon;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JMenu;
// import javax.swing.JMenuBar;
// import javax.swing.SwingConstants;

// public class TelaPrincipal {
// 	private JFrame frame;
// 	private JMenu mnPessoa;
// 	private JMenu mnAluno;
// 	private JMenu mnTelefone;
// 	private JMenu mnConsulta;
// 	private JLabel label;

// 	/**
// 	 * Launch the application.
// 	 */
// 	public static void main(String[] args) {
// 		EventQueue.invokeLater(new Runnable() {
// 			public void run() {
// 				try {
// 					TelaPrincipal window = new TelaPrincipal();
// 					window.frame.setVisible(true);
// 				} catch (Exception e) {
// 					e.printStackTrace();
// 				}
// 			}
// 		});
// 	}

// 	/**
// 	 * Create the application.
// 	 */
// 	public TelaPrincipal() {
// 		initialize();
// 	}

// 	/**
// 	 * Initialize the contents of the frame.
// 	 */
// 	private void initialize() {
// 		frame = new JFrame();
// 		frame.setTitle("ContratAe");
// 		frame.setBounds(100, 100, 450, 300);
// 		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// 		frame.getContentPane().setLayout(null);
// 		frame.setResizable(false);
		
// 		label = new JLabel("");
// 		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
// 		label.setHorizontalAlignment(SwingConstants.CENTER);
// 		label.setBounds(0, 0, 444, 249);
// 		label.setText("Inicializando...");
// 		label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
// 		// ImageIcon imagem = new ImageIcon(getClass().getResource("/imagens/agenda.jpg"));
// 		// imagem = new ImageIcon(imagem.getImage().getScaledInstance(label.getWidth(),label.getHeight(), Image.SCALE_DEFAULT));
// 		// label.setIcon(imagem);
// 		frame.getContentPane().add(label);

// 		JMenuBar menuBar = new JMenuBar();
// 		frame.setJMenuBar(menuBar);
// 		mnPessoa = new JMenu("Candidato");
// 		mnPessoa.addMouseListener(new MouseAdapter() {
// 			@Override
// 			public void mouseClicked(MouseEvent e) {
// 				new TelaCandidato();
// 			}
// 		});
// 		menuBar.add(mnPessoa);
		
// 		mnAluno = new JMenu("Vaga");
// 		mnAluno.addMouseListener(new MouseAdapter() {
// 			@Override
// 			public void mouseClicked(MouseEvent e) {
// 				new TelaCandidato();
// 			}
// 		});
// 		menuBar.add(mnAluno);

// 		// mnTelefone = new JMenu("Telefone");
// 		// mnTelefone.addMouseListener(new MouseAdapter() {
// 		// 	@Override
// 		// 	public void mouseClicked(MouseEvent e) {
// 		// 		new TelaTelefone();
// 		// 	}
// 		// });
// 		// menuBar.add(mnTelefone);
		
// 		// mnConsulta = new JMenu("Consulta");
// 		// mnConsulta.addMouseListener(new MouseAdapter() {
// 		// 	@Override
// 		// 	public void mouseClicked(MouseEvent e) {
// 		// 		new TelaConsulta();
// 		// 	}
// 		// });
// 		// menuBar.add(mnConsulta);
// 	}
// }
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
