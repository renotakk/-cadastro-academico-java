import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class aula05 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Properties config = new Properties();
        try (FileInputStream in = new FileInputStream("config.properties")) {
            config.load(in);
        } catch (IOException e) {
            System.out.println("Não encontrei o arquivo config.properties. Copie o config.properties.example e preencha com seus dados.");
            return;
        }

        String url = config.getProperty("db.url");
        String usuario = config.getProperty("db.user");
        String senha = config.getProperty("db.password");

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conectou com sucesso!");

            System.out.println("Digite a matricula do professor: ");
            String matricula = input.nextLine();
            System.out.println("Digite o nome do professor: ");
            String nomeProfessor = input.nextLine();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO professor (matricula, nome) VALUES (?, ?);");
            stmt.setString(1, matricula);
            stmt.setString(2, nomeProfessor);
            stmt.executeUpdate();

            System.out.println("Digite o nome da materia: ");
            String nomeMateria = input.nextLine();
            System.out.println("Digite quantas horas tem a materia: ");
            int horas = input.nextInt();

            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO materia (nome, horas) VALUES (?, ?);");
            stmt2.setString(1, nomeMateria);
            stmt2.setInt(2, horas);
            stmt2.executeUpdate();

            System.out.println("Digite o periodo da turma: ");
            int periodo = input.nextInt();
            System.out.println("Digite a quantidade de alunos: ");
            int qtdAlunos = input.nextInt();

            PreparedStatement stmt3 = conn.prepareStatement("SELECT id FROM materia WHERE nome = ?");
            stmt3.setString(1, nomeMateria);
            int idMateria = 0;
            ResultSet rs = stmt3.executeQuery();
            if (rs.next()) {
                idMateria = rs.getInt("id");
            }

            PreparedStatement stmt4 = conn.prepareStatement(
                    "INSERT INTO turma (periodo, qtd_alunos, matricula_professor, id_materia) VALUES (?, ?, ?, ?);");
            stmt4.setInt(1, periodo);
            stmt4.setInt(2, qtdAlunos);
            stmt4.setString(3, matricula);
            stmt4.setInt(4, idMateria);
            stmt4.executeUpdate();

            System.out.println("Dados cadastrados com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
