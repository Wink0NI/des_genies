package main.process;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SysGestion {
    public static void clear() {

        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void wait(int ms) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
