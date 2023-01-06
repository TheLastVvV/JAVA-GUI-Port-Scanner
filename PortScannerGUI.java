import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PortScannerGUI {
  public static void main(String[] args) {
    // Create the window
    JFrame window = new JFrame("Port Scanner");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setSize(400, 100);

    // Create the input panel
    JPanel inputPanel = new JPanel();
    inputPanel.setLayout(new BorderLayout());
    JLabel hostLabel = new JLabel("Host: ");
    final JTextField hostField = new JTextField();
    inputPanel.add(hostLabel, BorderLayout.WEST);
    inputPanel.add(hostField, BorderLayout.CENTER);

    // Create the button panel
    JPanel buttonPanel = new JPanel();
    JButton scanButton = new JButton("Scan");
    scanButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String host = hostField.getText();
        for (int i = 1; i <= 65535; i++) {
          try {
            Socket socket = new Socket(host, i);
            System.out.println("Port " + i + " is open on host " + host);
            socket.close();
          } catch (Exception ex) {
            // Port is closed or filtered
          }
        }
      }
    });
    buttonPanel.add(scanButton);

    // Add the panels to the window
    window.add(inputPanel, BorderLayout.NORTH);
    window.add(buttonPanel, BorderLayout.SOUTH);

    // Show the window
    window.setVisible(true);
  }
}
