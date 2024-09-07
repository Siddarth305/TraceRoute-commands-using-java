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
            // Check if the IP/hostname is valid before starting the traceroute
            if (isValidIPAddress(ipAddress)) {
                String command = isWindows() ? "tracert " : "traceroute ";
                Process process = Runtime.getRuntime().exec(command + ipAddress);
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String commandOutput;

                while ((commandOutput = inputStream.readLine()) != null) {
                    System.out.println(commandOutput);
                }

                int exitCode = process.waitFor(); // Ensure the process completes
                System.out.println("Traceroute finished with exit code: " + exitCode);
            } else {
                System.out.println("Invalid IP address or hostname entered. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    // Added a simple method to validate the IP address or hostname
    private static boolean isValidIPAddress(String ipAddress) {
        return ipAddress.matches(
            "^(([0-9]{1,3}\\.){3}[0-9]{1,3})|([a-zA-Z0-9]+\\.[a-zA-Z0-9]+(\\.[a-zA-Z]{2,6})?)$");
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }
}
