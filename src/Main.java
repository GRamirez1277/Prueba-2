import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main extends JFrame {
    private PalindromoAir palindromoAir;
    private JTextArea outputArea;
    private JTextField nameField;

    public Main() {
        palindromoAir = new PalindromoAir();
        initUI();
    }

    private void initUI() {
        setTitle("PalindromoAir");
        setSize(750, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JButton sellTicketButton = new JButton("Vender ticket");
        JButton cancelTicketButton = new JButton("Cancelar ticket");
        JButton dispatchButton = new JButton("Enviar avión");
        JButton printPassengersButton = new JButton("Listar pasajeros");

        buttonPanel.add(sellTicketButton);
        buttonPanel.add(cancelTicketButton);
        buttonPanel.add(dispatchButton);
        buttonPanel.add(printPassengersButton);

        nameField = new JTextField(20);
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        panel.add(new JLabel("Nombre del pasajero:"), BorderLayout.NORTH);
        panel.add(nameField, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.EAST);
        panel.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        add(panel);

        sellTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    String result = palindromoAir.sellTicket(name);
                    outputArea.append(result + "\n");
                }
            }
        });

        cancelTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                if (!name.isEmpty()) {
                    boolean resultado = palindromoAir.cancelTicket(name);
                    if (resultado) {
                        outputArea.append("Se ha cancelado el ticket de: " + name + "\n");
                    } else {
                        outputArea.append("No se ha encontrado al pasajero: " + name + "\n");
                    }
                }
            }
        });

        dispatchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double totalIncome = palindromoAir.income();
                outputArea.append("Ingreso total: $" + totalIncome + "\n");
                palindromoAir.dispatch();
                outputArea.append("El avión ha despegado\n");
            }
        });

        printPassengersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.append("Pasajeros a bordo:\n");
                for (int i = 0; i < 30; i++) {
                    Ticket ticket = palindromoAir.getAsientos()[i];
                    if (ticket != null) {
                        outputArea.append(ticket.print() + "\n");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}
