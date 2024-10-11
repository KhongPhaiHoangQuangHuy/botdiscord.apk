import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText tokenInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenInput = findViewById(R.id.tokenInput);
        Button startBotButton = findViewById(R.id.startBotButton);

        startBotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = tokenInput.getText().toString();
                runBot(token);
            }
        });
    }

    private void runBot(String token) {
        try {
            // Thiết lập môi trường để chạy bot
            ProcessBuilder processBuilder = new ProcessBuilder("node", "/path/to/bot.js");
            processBuilder.environment().put("TOKEN", token);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Đợi cho bot chạy
            new Thread(() -> {
                try {
                    process.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
