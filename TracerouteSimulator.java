package cn;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class TracerouteSimulator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the IP address or hostname to traceroute: ");
        String ipAddress = scanner.nextLine();

        try {
            System.out.println("Tracing route to " + ipAddress + "...");
            String command = isWindows() ? "tracert " : "traceroute ";
            Process process = Runtime.getRuntime().exec(command + ipAddress);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String commandOutput;

            while ((commandOutput = inputStream.readLine()) != null) {
                System.out.println(commandOutput);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
