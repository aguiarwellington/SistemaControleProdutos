package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Conexao {
    private static final String ENV_FILE = ".env";
    private static final HashMap<String, String> env = new HashMap<>();

    static {
        try (BufferedReader reader = new BufferedReader(new FileReader(ENV_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    env.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar .env: " + e.getMessage());
        }
    }

    public static Connection conectar() throws SQLException {
        String url = env.get("db.url");
        String user = env.get("db.user");
        String password = env.get("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}
